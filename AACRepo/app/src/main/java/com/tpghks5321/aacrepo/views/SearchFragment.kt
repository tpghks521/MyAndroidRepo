package com.tpghks5321.aacrepo.views

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.tpghks5321.aacrepo.R
import com.tpghks5321.aacrepo.databinding.FragmentSearchBinding
import com.tpghks5321.aacrepo.viewmodels.SearchViewModel
import com.tpghks5321.stunitasproject.adapter.PostLoadStateAdapter
import com.tpghks5321.stunitasproject.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class SearchFragment : Fragment() {

	private val viewModel: SearchViewModel by viewModels()
	lateinit var binding: FragmentSearchBinding
	lateinit var adapter: SearchAdapter
	private val searchJob = Job()

	override fun onCreateView(
			inflater: LayoutInflater,
			container: ViewGroup?,
			savedInstanceState: Bundle?
	): View? {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

		initAdapter()
		initSwipeToRefresh()
		initSearch()

		return binding.root
	}

	private fun initSearch() {
		binding.searchView.addTextChangedListener(textWatcher)
	}

	private fun initAdapter() {
		adapter = SearchAdapter()

		binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
				header = PostLoadStateAdapter(),
				footer = PostLoadStateAdapter()
		)

		lifecycleScope.launchWhenCreated {
			@OptIn(ExperimentalCoroutinesApi::class)
			adapter.loadStateFlow.collectLatest { loadStates ->
				binding.swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
			}
		}

		lifecycleScope.launchWhenCreated {
			@OptIn(ExperimentalCoroutinesApi::class)
			viewModel.posts.collectLatest {
				adapter.submitData(it)
			}
		}
	}

	private fun initSwipeToRefresh() {
		binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }
	}

	private val textWatcher = object : TextWatcher {
		override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
		}

		override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
		}

		override fun afterTextChanged(s: Editable?) {
			searchJob.cancelChildren()
			GlobalScope.launch(searchJob) {
				delay(1000)
				viewModel.search(s.toString())
				activity?.let { hideKeyboard(it) }
			}
			searchJob.start()
		}
	}

	fun hideKeyboard(activity: Activity) {
		val imm: InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
		var view = activity.currentFocus
		if (view == null) {
			view = View(activity)
		}
		imm.hideSoftInputFromWindow(view.windowToken, 0)
	}
}