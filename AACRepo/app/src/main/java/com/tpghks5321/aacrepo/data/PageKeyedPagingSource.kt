package com.tpghks5321.aacrepo.data


import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.awaitResponse

import androidx.paging.PagingSource.LoadResult.Page
import com.tpghks5321.aacrepo.api.AppService

class PageKeyedPagingSource(private val service: AppService, private val query: String) : PagingSource<Int, KakaoImage>() {


	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, KakaoImage> {
		return try {

			val key: Int = if (params.key == null) 1 else params.key!!

			val deferred = GlobalScope.async(Dispatchers.IO) {
				val data = service.getSearch(query, key, params.loadSize)
				val result = data.awaitResponse()
				if (result.isSuccessful) {
					result.body()

				} else {
					throw  Exception(result.message() + result.code())
				}
			}

			val msg = deferred.await()

			val beforeKey = if (key - 1 == 0) null else key - 1
			val afterKey = key + 1

			Page(data = msg!!.documents!!, prevKey = beforeKey, nextKey = afterKey)

		} catch (e: Exception) {
			LoadResult.Error(e)
		}

	}

	override fun getRefreshKey(state: PagingState<Int, KakaoImage>): Int? {
		return 1
	}

}