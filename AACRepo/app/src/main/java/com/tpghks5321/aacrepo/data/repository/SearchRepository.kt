package com.tpghks5321.aacrepo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.tpghks5321.aacrepo.api.AppService
import com.tpghks5321.aacrepo.data.PageKeyedPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val appService: AppService
) {

    fun requestSearch(query: String, pageSize: Int) = Pager(
            PagingConfig(pageSize)
    ) {
        PageKeyedPagingSource(
            appService
            ,query
        )
    }.flow
}