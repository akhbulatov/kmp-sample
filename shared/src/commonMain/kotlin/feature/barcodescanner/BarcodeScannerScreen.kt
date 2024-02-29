package feature.barcodescanner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object BarcodeScannerScreen : Screen {

    @Composable
    override fun Content() {
        val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
        val controller: PermissionsController =
            remember(factory) { factory.createPermissionsController() }
        val coroutineScope: CoroutineScope = rememberCoroutineScope()

        MaterialTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TopAppBar(
                    title = {
                        Text(text = "Barcode Scanner")
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    BarcodeScannerCamera()
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                controller.providePermission(Permission.CAMERA)
                            }
                        },
                        modifier = Modifier
                            .align(alignment = Alignment.BottomCenter),
                        content = {
                            Text(text = "Scan")
                        }
                    )
                }
            }
        }
    }
}