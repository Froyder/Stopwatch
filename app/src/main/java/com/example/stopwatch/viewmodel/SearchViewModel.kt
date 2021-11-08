package com.example.stopwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class SearchViewModel: ViewModel() {

    private val _searchLiveData = MutableLiveData<String>()
    val searchLiveData: LiveData<String>
        get() = _searchLiveData

    private val job: Job = Job()
    private val searchCoroutineScope = CoroutineScope(Dispatchers.Main + job)
    private val queryStateFlow = MutableStateFlow("")

    init {
        setUpSearchStateFlow()
    }

    fun setQuery(query: String) {
        queryStateFlow.value = query
    }

    fun setChangedQuery(newText: String) {
        queryStateFlow.value = newText
    }

    private fun setUpSearchStateFlow() {
        searchCoroutineScope.launch {
            queryStateFlow.debounce(DEBOUNCE_TIME)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    getDelayedData(query)
                        .catch {
                            emit("")
                        }
                }
                .collect { result -> _searchLiveData.postValue(result) }
        }
    }

    private fun getDelayedData(query: String): Flow<String> {
        return flow {
            delay(DELAY_TIME)
            emit(query)
        }
    }

    companion object {
        private const val DELAY_TIME: Long = 1500
        private const val DEBOUNCE_TIME: Long = 500
    }

}