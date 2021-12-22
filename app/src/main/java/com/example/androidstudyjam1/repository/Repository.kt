package com.example.androidstudyjam1.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.androidstudyjam1.local.AppDatabase
import com.example.androidstudyjam1.network.Retrofit

object Repository {
    val newsApi by lazy { Retrofit.newsApi }

    //    suspend fun getAllNews() = safeApiCall(Dispatchers.IO) { newsApi.getLatestNews(1) }
    fun getSearchResults(query: String? = null) =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = 40,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GetNewsPagingSource(newsApi, query) }
        ).flow

    const val NETWORK_PAGE_SIZE = 10

    fun getAllSavedNewsDao(context: Context) = AppDatabase.invoke(context).getSavedNewsDao()
}