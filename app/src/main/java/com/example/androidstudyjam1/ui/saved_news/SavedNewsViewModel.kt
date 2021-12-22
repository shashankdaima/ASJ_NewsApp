package com.example.androidstudyjam1.ui.saved_news

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.androidstudyjam1.local.SavedNewsDao
import com.example.androidstudyjam1.repository.Repository

class SavedNewsViewModel(context: Context) : ViewModel() {
    private var savedMessageDao: SavedNewsDao = Repository.getAllSavedNewsDao(context)
    val listOfSavedNews = savedMessageDao.getAllSavedNews()

}