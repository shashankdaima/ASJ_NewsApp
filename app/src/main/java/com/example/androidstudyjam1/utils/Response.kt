package com.example.androidstudyjam1.utils

sealed class Response<T>(
    val data: T? = null,
    val exception: Exception? = null
) {
    class Loading<T>(data: T) : Response<T>(data)
    class Error<T>(exception: Exception) : Response<T>(exception = exception)
    class Success<T>(data: T) : Response<T>(data)
}