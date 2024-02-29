package feature.barcodescanner

import android.media.Image
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import io.github.aakira.napier.Napier

class BarcodeScannerAnalyzer(
    private val onQrCodeScanned: (String) -> Unit
) : ImageAnalysis.Analyzer {

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage: Image? = imageProxy.image

        if (mediaImage != null) {
            val image: InputImage =
                InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            val scanner: BarcodeScanner = BarcodeScanning.getClient()
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.size > 0) {
                        when (barcodes[0].valueType) {
                            Barcode.TYPE_URL -> barcodes[0].rawValue?.let { onQrCodeScanned(it) }
                            Barcode.TYPE_ISBN -> barcodes[0].rawValue?.let { onQrCodeScanned(it) }
                            Barcode.TYPE_TEXT -> barcodes[0].rawValue?.let { onQrCodeScanned(it) }
                            else -> barcodes[0].rawValue?.let { onQrCodeScanned("Type not supported") }
                        }
                        Napier.d(tag = "position", message = barcodes[0].boundingBox.toString())
                    }
                    imageProxy.close()
                    scanner.close()
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    imageProxy.close()
                    scanner.close()
                }
        }

    }
}