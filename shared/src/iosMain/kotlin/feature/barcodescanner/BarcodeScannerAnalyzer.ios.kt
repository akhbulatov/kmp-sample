package feature.barcodescanner

import platform.UIKit.UIImage

class BarcodeScannerAnalyzer(
    private val onQrCodeScanned: (String) -> Unit
) {

    fun analyze(image: UIImage) {
//        val visionImage = MLKVisionImage(image)
//        visionImage.orientation = image.imageOrientation
//
//
//        val barcodeScanner: MLKBarcodeScanner = MLKBarcodeScanner.barcodeScanner()
//        barcodeScanner.processImage(image = visionImage as MLKCompatibleImageProtocol) {features, error ->
//            Napier.d(tag = "BarcodeScannerAnalyzer", message ="processImage:\n\tfeatures=$features\n\terror=$error")
//            val rawValue = features?.firstOrNull()
//            onQrCodeScanned(rawValue.toString())
//        }
    }
}