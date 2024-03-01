package feature.barcodescanner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVCaptureDeviceInput
import platform.AVFoundation.AVCaptureMetadataOutput
import platform.AVFoundation.AVCaptureSession
import platform.AVFoundation.AVCaptureVideoPreviewLayer
import platform.AVFoundation.AVMediaTypeVideo
import platform.CoreGraphics.CGRectMake
import platform.UIKit.UIColor
import platform.UIKit.UIView

@Composable
actual fun BarcodeScannerCamera() {
    var code by remember { mutableStateOf(value = "") }

    // 1. Настроим сессию
    val session = remember { AVCaptureSession() }
    // 2. Настраиваем устройство видео
    val captureDevice =
        remember { AVCaptureDevice.defaultDeviceWithMediaType(mediaType = AVMediaTypeVideo) }
    // 3. Настроим input
//    try {
    val input = remember { AVCaptureDeviceInput(device = captureDevice!!, error = null) }
    session.addInput(input = input)
//    } catch (e: Exception) {

//    }
    // 4. Настроим output
    val output = remember { AVCaptureMetadataOutput() }
    session.addOutput(output)

//    output.setMetadataObjectsDelegate(
//        objectsDelegate = object : NSObject(), AVCaptureMetadataOutputObjectsDelegateProtocol {
//            override fun captureOutput(
//                output: AVCaptureOutput,
//                didOutputMetadataObjects: List<*>,
//                fromConnection: AVCaptureConnection
//            ) {
//
//            }
//        },
//        queue = dispatch_queue_main_t()
//    )
//    output.metadataObjectTypes = listOf(AVMetadataObjectTypeQRCode)
    output.metadataObjectTypes = output.availableMetadataObjectTypes

    val video = remember { AVCaptureVideoPreviewLayer(sessionWithNoConnection = session) }

    val view = remember { UIView() }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        UIKitView(
            factory = {
                view.backgroundColor = UIColor.blueColor

                video.backgroundColor = UIColor.redColor().CGColor
                video.frame = CGRectMake(x = 0.0, y = 100.0, width = 200.0, height = 200.0)
//                video.frame = view.layer.bounds
                view
            },
//            onResize = { view: UIView, rect: CValue<CGRect> ->
//                video.frame = view.layer.bounds
//            },
//            update = { view: UIView ->
//                session.startRunning()
//            },
            modifier = Modifier
                .size(400.dp),
        )
        Text(text = "Code: $code")
        Button(
            onClick = {
                view.layer.addSublayer(video)
                CoroutineScope(Dispatchers.Default).launch {
                    session.startRunning()
                }
            },
        ) {
            Text("Click my scan")
        }
    }
}