package com.example.androidstudyjam1.repository

import com.example.androidstudyjam1.network.Retrofit

class Repository {
    val newsApi by lazy { Retrofit.newsApi }
    suspend fun getAllNews() = newsApi.getLatestNews()
}