package com.fin.sampletest.api

sealed class GenericResponse<out T: Any> {
    data class Success<out T : Any>(val data: T) : GenericResponse<T>()
    data class Error(val exception: Exception) : GenericResponse<Nothing>()
}