package com.example.androidstudyjam1.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidstudyjam1.models.ApiResponse
import com.example.androidstudyjam1.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityViewModel : ViewModel() {
    val responsitory = Repository()
    val list = MutableLiveData<ApiResponse>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            list.postValue(responsitory.newsApi.getLatestNews())
        }
    }
}