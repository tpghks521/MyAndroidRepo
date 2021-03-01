package com.tpghks5321.aacrepo.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.paging.PagingData
import com.tpghks5321.aacrepo.data.KakaoImage
import com.tpghks5321.aacrepo.data.repository.SearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class SearchViewModel @ViewModelInject constructor(private val searchRepository: SearchRepository) :
		ViewModel() {

	//    val searchList : LiveData<PagingData<KakaoImage>> =
	val liveData = MutableLiveData<String>()
	private val clearlistch = Channel<Unit>(Channel.CONFLATED)


	@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
	val posts = flowOf(
			clearlistch.consumeAsFlow().map { PagingData.empty<KakaoImage>() },
			liveData.asFlow().flatMapLatest { query -> searchRepository.requestSearch(query, 20) }
	).flattenMerge(2)


	fun search(query: String) {
		liveData.postValue(query)
	}

}