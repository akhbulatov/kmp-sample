package feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import domain.model.SearchRecentQuery

object SearchScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { SearchScreenModel.create() }
        val state by screenModel.state.collectAsState()

        MaterialTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TopAppBar(
                    title = {
                        Text("Поиск")
                    }
                )
                TextField(
                    value = screenModel.queryInput,
                    onValueChange = { screenModel.onSearchQueryChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    when {
                        !state.emptyError.isNullOrBlank() -> {
                            Text(
                                text = state.emptyError!!,
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                            )
                        }

                        state.recentQueries.isNotEmpty() -> {
                            SearchRecentQueryList(list = state.recentQueries)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchRecentQueryList(list: List<SearchRecentQuery>) {
    LazyColumn {
        items(
            count = list.size
        ) { index ->
            SearchRecentQueryListItem(item = list[index])
        }
    }
}

@Composable
private fun SearchRecentQueryListItem(item: SearchRecentQuery) {
    Box {
        Text(
            modifier = Modifier
                .padding(15.dp),
            text = item.text
        )
    }
}