package feature.location

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen

object LocationScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { LocationScreenModel.create() }
        val state by screenModel.state.collectAsState()

        MaterialTheme {
            TopAppBar(
                title = {
                    Text(text = "Location")
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Текущее местоположение:" +
                            "\n${state.currentLocation?.latitude}," +
                            "\n${state.currentLocation?.longitude}",
                )
            }
        }
    }
}