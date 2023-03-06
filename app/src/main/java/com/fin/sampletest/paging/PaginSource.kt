/*
package com.fin.sampletest.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fin.sampletest.data.RepoResponse
import com.fin.sampletest.database.dao.RepoDAO

class PaginSource (private val userPostDao: RepoDAO) : PagingSource<Int, RepoResponse>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 0
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, RepoResponse> {
        val position = params.key ?: INITIAL_PAGE_INDEX
        val randomPosts = userPostDao.getRandomPosts(params.loadSize)
        return PagingSource.LoadResult.Page(
            data = randomPosts,
            prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
            nextKey = if (randomPosts.isNullOrEmpty()) null else position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, RepoResponse>): Int? = null
}*/
