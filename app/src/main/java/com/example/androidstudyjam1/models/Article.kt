package com.example.androidstudyjam1.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "saved_article_table")
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String,
    val currTime: Long = System.currentTimeMillis()
) : Parcelable