package com.example.androidstudyjam1.ui.saved_news

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SavedMessageViewModelFactory(
    private val context: Context,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SavedNewsViewModel(context) as T
    }
}