package feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import di.CommonFactoryProvider
import domain.repository.SearchRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchScreenModel(
    private val searchRepository: SearchRepository
) : StateScreenModel<SearchUiState>(SearchUiState()) {

    var queryInput: String by mutableStateOf(value = "")
        private set

    init {
        observeSearchRecentQueries()
    }

    private fun observeSearchRecentQueries() {
        searchRepository.getRecentSearchQueries()
            .onEach { recentQueries ->
                mutableState.update { state ->
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
            .launchIn(screenModelScope)
    }

    fun onSearchQueryChanged(query: String) {
        queryInput = query
        screenModelScope.launch {
            if (query.length > 4) {
                searchRepository.searchByQuery(query)
            }
        }
    }

    companion object {
        fun create(): SearchScreenModel {
            return SearchScreenModel(
                searchRepository = CommonFactoryProvider.commonFactory.searchRepository
            )
        }
    }
}