package com.fin.sampletest.api

import com.fin.sampletest.BuildConfig
import com.google.gson.GsonBuilder
import com.fin.sampletest.api.UnsafeOkHttpClient.unsafeOkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    //test
    private const val BASE_URL = BuildConfig.BASE_URL
    const val TIME_OUT: Long = 120

    fun makeRetrofitService(): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
                )
            )
            .client(unsafeOkHttpClient)
            .build().create(Api::class.java)
    }

    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .client(unsafeOkHttpClient)
            .build()
    }

    fun makeRetrofitSSOService(): Api {
        return retrofit().create(Api::class.java)
    }


    private var retryCount = 0

    fun <T> AddToEnqueue(baseCall: Call<T>, listener: ApiRequestListener?, ReqType: Int) {
        baseCall.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
                try {
                    if (null != response.body()) {
                        if (response.code() == 200) {
                            listener?.onReceiveResult(ReqType, response)
                        } else {
                            retryCount++
                            if (retryCount <= 1) {
                                call.clone().enqueue(this)
                            } else {
                                listener?.onReceiveError(ReqType, "2")
                                call.cancel()
                            }
                        }
                    } else {
                        retryCount++
                        if (retryCount <= 1) {
                            call.clone().enqueue(this)
                        } else {
                            listener?.onReceiveError(ReqType, "3")
                            call.cancel()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                try {
                    if (call.isCanceled || "Canceled" == t.message) return
                    retryCount++
                    if (retryCount <= 1) {
                        call.clone().enqueue(this)
                    } else {
                        listener?.onReceiveError(ReqType, "1")
                        call.cancel()
                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

}