package com.example.androidstudyjam1.local

import androidx.room.*
import com.example.androidstudyjam1.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(t: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(t: List<Article>)

    @Delete
    suspend fun delete(t: Article)

    @Update
    suspend fun update(t: Article)

    @Query("SELECT * FROM saved_article_table ORDER BY currTime DESC")
    fun getAllSavedNews(): Flow<List<Article>>

}