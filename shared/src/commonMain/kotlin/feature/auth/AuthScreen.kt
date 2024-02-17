package feature.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen

object AuthScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { AuthScreenModel.create() }
        val state by screenModel.state.collectAsState()
        var loginInput: String by mutableStateOf(value = "")

        MaterialTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TopAppBar(
                    title = {
                        Text(text = "Авторизация")
                    }
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                ) {
                    Text(
                        text = "Login: ${state.login.orEmpty()}",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.h4
                    )
                    TextField(
                        value = loginInput,
                        onValueChange = { loginInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    )
                    Button(
                        onClick = { screenModel.onSaveClick(loginInput) },
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(top = 15.dp)

                    ) {
                        Text(text = "Сохранить")
                    }
                }
            }
        }
    }
}