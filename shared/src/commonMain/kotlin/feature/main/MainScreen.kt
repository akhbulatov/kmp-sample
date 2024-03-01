package feature.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import feature.auth.AuthScreen
import feature.barcodegenerator.BarcodeGeneratorScreen
import feature.barcodescanner.BarcodeScannerScreen
import feature.location.LocationScreen
import feature.repo.ReposScreen
import feature.search.SearchScreen

object MainScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        MaterialTheme {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navigator.push(ReposScreen) }
                ) {
                    Text(text = "Network")
                }
                Button(
                    onClick = { navigator.push(SearchScreen) }
                ) {
                    Text(text = "Database")
                }
                Button(
                    onClick = { navigator.push(AuthScreen) }
                ) {
                    Text(text = "Preferences")
                }
                Button(
                    onClick = { navigator.push(LocationScreen) }
                ) {
                    Text(text = "Location")
                }
                Button(
                    onClick = { navigator.push(BarcodeGeneratorScreen) }
                ) {
                    Text(text = "Barcode Generator")
                }
                Button(
                    onClick = { navigator.push(BarcodeScannerScreen) }
                ) {
                    Text(text = "Barcode Scanner")
                }
            }
        }
    }
}