package com.example.androidstudyjam1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidstudyjam1.models.SuccessModel
import com.example.androidstudyjam1.network.NetworkResponse
import com.example.androidstudyjam1.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ActivityViewModel : ViewModel() {
    private val repository = Repository()
    private val _list = MutableLiveData<SuccessModel>()
    val list: LiveData<SuccessModel>
        get() = _list

    private val _eventChannel = Channel<Event>()
    val event = _eventChannel.receiveAsFlow()

    init {
        getLatestNews()
    }

    fun getLatestNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _eventChannel.send(Event.Loading)
            when (val response = repository.getAllNews()) {
                is NetworkResponse.GenericError -> {
                    if (response.error != null) {
                        _eventChannel.send(Event.ErrorResponse(response.error.message))
                    } else {
                        _eventChannel.send(Event.UnknownError)
                    }
                }
                NetworkResponse.NetworkError -> {
                    _eventChannel.send(Event.InternetError)
                }
                is NetworkResponse.Success -> {
                    _eventChannel.send(Event.NoError)
                    response.value.let {
                        _list.postValue(it)
                    }
                }
            }
        }
    }

    sealed class Event {
        object Loading : Event()
        object UnknownError : Event()
        object InternetError : Event()
        class ErrorResponse(val message: String) : Event()
        object NoError : Event()
    }
}