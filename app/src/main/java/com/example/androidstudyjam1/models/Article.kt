package com.example.androidstudyjam1.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "saved_article_table")
data class Article(
    @ColumnInfo(name = "author_row", defaultValue = "No Author Provided")
    val author: String,
    @ColumnInfo(name = "content_row", defaultValue = "No Content Provided")
    val content: String,
    @ColumnInfo(name = "description_row", defaultValue = "No Description Provided")
    val description: String,
    @ColumnInfo(name = "publish_date_row", defaultValue = "No Date Provided")
    val publishedAt: String,
    @ColumnInfo(name = "title_row", defaultValue = "No Title Provided")
    val title: String,
    @PrimaryKey
    @ColumnInfo(name = "url_row")
    val url: String,
    @ColumnInfo(name = "image_url_row", defaultValue = "no_image_provided")
    val urlToImage: String,
    val currTime: Long = System.currentTimeMillis()
) : Parcelable