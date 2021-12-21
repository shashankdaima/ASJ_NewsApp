package com.example.androidstudyjam1.models

data class SuccessModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)