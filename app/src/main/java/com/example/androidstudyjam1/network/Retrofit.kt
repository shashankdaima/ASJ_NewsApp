package com.example.androidstudyjam1.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val retrofitInstance by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val newsApi = retrofitInstance.create(NewsApi::class.java)
}