package com.fin.sampletest.ui.home

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fin.sampletest.api.ResponseCallback
import com.fin.sampletest.data.RepoResponse
import com.fin.sampletest.database.dao.RepoDAO


class HomeRepository (  var db: RepoDAO) {

    var database = db
/*    fun getPagedList(config: PagedList.Config?): LiveData<PagedList<RepoResponse>?>? {
        val factory: DataSource.Factory<Int?, RepoResponse?>? = database.getRepoDetailsPaged()
        return LivePagedListBuilder<Any, Any>(factory, config!!)
            .build()
}*/

    fun getRepoListRepo(): List<RepoResponse> {
        return database.getAllDetails()
    }

}