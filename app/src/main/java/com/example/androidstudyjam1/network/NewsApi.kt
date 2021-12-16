package com.example.androidstudyjam1.network

import com.example.androidstudyjam1.models.Response
import retrofit2.http.GET

interface NewsApi {


    @GET("v2/top-headlines?country=in&pageSize=20")
    suspend fun getLatestNews(): Response

}
