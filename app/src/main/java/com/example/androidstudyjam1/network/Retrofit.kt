package com.example.androidstudyjam1.network

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Retrofit {
    private val retrofitInstance by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }
    val newsApi = retrofitInstance.create(NewsApi::class.java)

}