package com.example.androidstudyjam1.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.androidstudyjam1.models.Article
import com.example.androidstudyjam1.models.SuccessModel
import com.example.androidstudyjam1.network.NewsApi
import com.example.androidstudyjam1.repository.Repository.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

private const val INITIAL_PAGE_KEY = 1

class GetNewsPagingSource(
    private val service: NewsApi,
    private val query: String? = null
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: INITIAL_PAGE_KEY
        val apiQuery = query
        return try {
            val response =
                if (apiQuery != null)
                    (service.searchNews(apiQuery, position, params.loadSize))
                else {
                    service.getLatestNews(position, params.loadSize)
                }
            val repos = response.articles
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (position == INITIAL_PAGE_KEY) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}