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

    private val _query = MutableStateFlow<String?>(null)
    val query: StateFlow<String?>
        get() = _query

    private val _eventChannel = Channel<Event>()
    val event = _eventChannel.receiveAsFlow()


    fun changeQuery(newQuery: String?) {
        _query.value = newQuery
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val newsArticles = query.flatMapLatest {
        repository.getSearchResults(it)

    }



    sealed class Event {
        object Loading : Event()
        object UnknownError : Event()
        object InternetError : Event()
        class ErrorResponse(val message: String) : Event()
        object NoError : Event()
    }
}