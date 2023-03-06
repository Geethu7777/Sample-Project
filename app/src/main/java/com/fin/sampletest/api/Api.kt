package com.fin.sampletest.api

import com.fin.sampletest.data.RepoResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {
    @GET("/orgs/android/repos")
    fun makeLoginCall( ): Call<List<RepoResponse>>
}
