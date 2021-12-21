package com.example.androidstudyjam1.repository

sealed class RepositoryResponse<T>(
    val data: T? = null,
    val exception: Exception? = null
) {
    class Loading<T>(data: T) : RepositoryResponse<T>(data)
    class Error<T>(exception: Exception) : RepositoryResponse<T>(exception = exception)
    class Success<T>(data: T) : RepositoryResponse<T>(data)
}