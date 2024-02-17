package feature.search

import domain.model.SearchRecentQuery

data class SearchUiState(
    val emptyError: String? = null,
    val recentQueries: List<SearchRecentQuery> = emptyList()
)