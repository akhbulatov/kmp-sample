package org.example.kmpsample.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import domain.repository.SearchRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.kmpsample.KmpSampleApp

class SearchViewModel(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    init {
        observeSearchRecentQueries()
    }

    private fun observeSearchRecentQueries() {
        searchRepository.getRecentSearchQueries()
            .onEach { recentQueries ->
                _uiState.update { state ->
                    state.copy(
                        emptyError = if (recentQueries.isEmpty()) {
                            "Нет недавних поисковых запросов"
                        } else {
                            null
                        },
                        recentQueries = recentQueries
                    )
                }
            }
            .catch { e ->
                Napier.e(message = e.message.orEmpty())
            }
            .launchIn(viewModelScope)
    }

    fun onSearchQueryChanged(query: String) {
        viewModelScope.launch {
            if (query.length > 4) {
                searchRepository.searchByQuery(query)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                @Suppress("UNCHECKED_CAST")
                return SearchViewModel(
                    searchRepository = KmpSampleApp.commonFactory.searchRepository
                ) as T
            }
        }
    }
}