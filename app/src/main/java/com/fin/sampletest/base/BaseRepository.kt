package com.fin.sampletest.base

import android.util.Log
import com.fin.sampletest.api.GenericResponse
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result: GenericResponse<T> = apiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is GenericResponse.Success ->
                data = result.data
            is GenericResponse.Error -> {
                Log.d("RemoteDataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }

        return data

    }

    private suspend fun <T : Any> apiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): GenericResponse<T> {
        val response = call.invoke()
        if (response.isSuccessful) return GenericResponse.Success(response.body()!!)

        return GenericResponse.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}