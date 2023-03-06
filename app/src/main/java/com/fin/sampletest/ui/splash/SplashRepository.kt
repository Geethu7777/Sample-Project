package com.fin.sampletest.ui.splash

import android.util.Log
import com.fin.sampletest.api.Api
import com.fin.sampletest.api.ResponseCallback
import com.fin.sampletest.data.RepoResponse
import com.fin.sampletest.database.dao.RepoDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashRepository (private val api: Api, private val callBack: ResponseCallback,   var db: RepoDAO ) {
    var database = db

    fun getRepoList()  {

        val call = api.makeLoginCall()
        call.enqueue(object : Callback<List<RepoResponse>> {
            override fun onResponse(
                call: Call<List<RepoResponse>>,
                response: Response<List<RepoResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        updateDataToDatabase(response.body() as List<RepoResponse>)
                    } ?: callBack.onFailure(response.message(), "reporead")
                } else {
                    callBack.onFailure(response.message(), "reporead")
                }
            }
            override fun onFailure(call: Call<List<RepoResponse>>, t: Throwable) {
                Log.e("t", t.printStackTrace().toString())
            }
        })
    }

    fun updateDataToDatabase(data : List<RepoResponse>) {

        CoroutineScope(IO).launch {
            for (item in data) {
                database.saveBRepoDetails(item)
            }

            callBack.onSuccess(null,"Completed")
        }
    }

}