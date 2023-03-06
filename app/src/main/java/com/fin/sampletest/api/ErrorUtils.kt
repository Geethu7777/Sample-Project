package com.fin.sampletest.api

import com.fin.sampletest.api.ApiFactory.retrofit
import retrofit2.Response
import java.io.IOException

object ErrorUtils {
    fun parseError(response: Response<*>): APIError? {
        val converter = retrofit()
            .responseBodyConverter<APIError>(
                APIError::class.java,
                arrayOfNulls(0)
            )
        val error: APIError
        error = try {
            converter.convert(response.errorBody())!!
        } catch (e: IOException) {
            return APIError()
        }
        return error
    }





}