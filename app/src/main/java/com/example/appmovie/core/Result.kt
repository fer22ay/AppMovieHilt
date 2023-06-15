package com.example.appmovie.core

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val message: String) : Result<kotlin.Nothing>()

    object Loading : Result<kotlin.Nothing>()

    object Nothing : Result<kotlin.Nothing>()

}