package com.example.appmovie.core

import retrofit2.Response

object RetrofitHandleCall {

    fun <T> handleCallResponse(response: Response<T>): Result<T> {
        if (response.isSuccessful) response.body()
            ?.let { resultResponse -> return Result.Success(resultResponse) }
        return Result.Error(response.message())
    }

}