package com.fin.sampletest.api

import retrofit2.Response

interface ApiRequestListener {
    fun onReceiveResult(ReqType: Int, response: Response<*>?)
    fun onReceiveError(ReqType: Int, Error: String?)
}