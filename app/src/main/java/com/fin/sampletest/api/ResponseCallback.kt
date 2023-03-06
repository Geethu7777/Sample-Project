package com.fin.sampletest.api

interface ResponseCallback {
    fun onSuccess(data: Any?, from: String)
    fun onFailure(data: Any, from: String)
}