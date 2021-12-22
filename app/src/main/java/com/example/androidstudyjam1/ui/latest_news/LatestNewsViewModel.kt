package com.example.androidstudyjam1.ui.latest_news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidstudyjam1.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LatestNewsViewModel : ViewModel() {
    private val repository = Repository

    private val _query = MutableStateFlow<String?>("Modi")
    val query: StateFlow<String?>
        get() = _query

    private val _eventChannel = Channel<Event>()
    val event = _eventChannel.receiveAsFlow()

    init {
        getLatestNews()
    }

    fun changeQuery(newQuery: String?) {
        _query.value = newQuery
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val newsArticles = query.flatMapLatest {
        repository.getSearchResults(it)
    }

    fun getLatestNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _eventChannel.send(Event.Loading)
//            when (val response = repository.getSearchResults()) {
//                is NetworkResponse.GenericError -> {
//                    if (response.error != null) {
//                        _eventChannel.send(Event.ErrorResponse(response.error.message))
//                    } else {
//                        _eventChannel.send(Event.UnknownError)
//                    }
//                }
//                NetworkResponse.NetworkError -> {
//                    _eventChannel.send(Event.InternetError)
//                }
//                is NetworkResponse.Success -> {
//                    _eventChannel.send(Event.NoError)
//                    response.value.let {
//                        _list.postValue(it)
//                    }
//                }
//            }

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