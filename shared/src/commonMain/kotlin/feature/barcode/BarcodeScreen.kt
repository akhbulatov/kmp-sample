package feature.barcode

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import io.github.alexzhirkevich.qrose.rememberQrCodePainter

object BarcodeScreen : Screen {

    @Composable
    override fun Content() {
        MaterialTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TopAppBar(
                    title = {
                        Text(text = "Barcode")
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberQrCodePainter(data = "https://example.com"),
                        contentDescription = "QR code"
                    )
                }
            }
        }
    }
}