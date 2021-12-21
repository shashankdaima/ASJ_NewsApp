package com.example.androidstudyjam1.network

import com.example.androidstudyjam1.models.ErrorModel

sealed class NetworkResponse<out T> {
    data class Success<out T>(val value: T): NetworkResponse<T>()
    data class GenericError(val code: Int? = null, val error: ErrorModel? = null): NetworkResponse<Nothing>()
    object NetworkError: NetworkResponse<Nothing>()
}