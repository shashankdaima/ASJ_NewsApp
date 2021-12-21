package com.example.androidstudyjam1.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ActivityViewModel : ViewModel() {
    private val _isOnlineStatus = MutableStateFlow(false)
    val isOnlineStatus: StateFlow<Boolean>
        get() = _isOnlineStatus

    fun setNetworkStatus(status: Boolean) {
        _isOnlineStatus.value = status
    }
}