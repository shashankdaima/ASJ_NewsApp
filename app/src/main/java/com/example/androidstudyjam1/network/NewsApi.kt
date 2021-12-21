package com.example.androidstudyjam1.network

import com.example.androidstudyjam1.BuildConfig
import com.example.androidstudyjam1.models.SuccessModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsApi {

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

    @Headers("X-Api-Key: $API_KEY")
    @GET("v2/top-headlines?country=in&pageSize=20")
    suspend fun getLatestNews(): SuccessModel

}
