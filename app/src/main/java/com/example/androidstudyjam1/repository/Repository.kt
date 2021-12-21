package com.example.androidstudyjam1.repository

import com.example.androidstudyjam1.network.Retrofit
import com.example.androidstudyjam1.utils.safeApiCall
import kotlinx.coroutines.Dispatchers

class Repository {
    val newsApi by lazy { Retrofit.newsApi }
    suspend fun getAllNews() = safeApiCall(Dispatchers.IO) { newsApi.getLatestNews() }
}