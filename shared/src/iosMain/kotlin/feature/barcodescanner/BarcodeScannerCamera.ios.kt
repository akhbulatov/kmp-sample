package feature.barcodescanner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import platform.UIKit.UIImage
import platform.UIKit.UIImagePickerController
import platform.UIKit.UIImagePickerControllerDelegateProtocol
import platform.UIKit.UIImagePickerControllerSourceType
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.darwin.NSObject

@Composable
actual fun BarcodeScannerCamera() {
    var code by remember { mutableStateOf(value = "") }
    val imagePickerController = remember { UIImagePickerController() }
    imagePickerController.sourceType =
        UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeCamera
    imagePickerController.delegate = object : NSObject(), UIImagePickerControllerDelegateProtocol,
        UINavigationControllerDelegateProtocol {
        override fun imagePickerController(
            picker: UIImagePickerController,
            didFinishPickingImage: UIImage,
            editingInfo: Map<Any?, *>?
        ) {
            val analyzer = BarcodeScannerAnalyzer {scannedCode ->
                code = scannedCode
            }
            analyzer.analyze(didFinishPickingImage)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        UIKitView(
            factory = {
                imagePickerController.view
            },
            modifier = Modifier
                .fillMaxSize()
        )
        Text(text = "Code: $code")
    }
}