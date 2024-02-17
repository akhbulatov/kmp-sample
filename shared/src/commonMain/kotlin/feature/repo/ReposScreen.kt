package feature.repo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import domain.model.Repo

object ReposScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { ReposScreenModel.create() }
        val state by screenModel.state.collectAsState()

        MaterialTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TopAppBar(
                    title = {
                        Text(text = "Репозитории")
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    when {
                        state.emptyProgress -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                            )
                        }

                        !state.emptyError.isNullOrBlank() -> {
                            Text(
                                text = state.emptyError.orEmpty(),
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                            )
                        }

                        state.repos.isNotEmpty() -> {
                            RepoList(list = state.repos)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RepoList(list: List<Repo>) {
    LazyColumn {
        items(
            count = list.size
        ) { index ->
            RepoListItem(item = list[index])
        }
    }
}

@Composable
private fun RepoListItem(item: Repo) {
    Column(
        modifier = Modifier
            .padding(15.dp)
    ) {
        Text(
            text = item.name,
            fontSize = 16.sp
        )
        Text(
            text = item.description.orEmpty(),
            modifier = Modifier
                .padding(top = 8.dp),
            color = Color(color = 0x99000000),
            fontSize = 14.sp
        )
    }
}