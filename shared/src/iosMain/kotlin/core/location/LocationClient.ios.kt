package core.location

import domain.model.Location
import io.github.aakira.napier.Napier
import kotlinx.cinterop.useContents
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLDistanceFilterNone
import platform.CoreLocation.kCLLocationAccuracyBest
import platform.Foundation.NSError
import platform.darwin.NSObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

// https://medium.com/rapido-labs/building-a-kotlin-multiplatform-mobile-sdk-for-location-related-services-488a2855ab23
actual class LocationClient {

    private val locationManager = CLLocationManager()

    actual suspend fun getCurrentLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            Napier.d(tag = TAG, message = "get current location.")
            locationManager.requestWhenInUseAuthorization()
            locationManager.desiredAccuracy = kCLLocationAccuracyBest
            locationManager.distanceFilter = kCLDistanceFilterNone
            locationManager.startUpdatingLocation()

            val locationDelegate = LocationDelegate()
            locationDelegate.onLocationUpdate = { location ->
                Napier.d(tag = TAG, message = "onLocationUpdate, location=$location.")
                locationManager.stopUpdatingLocation()
                if (continuation.isActive) {
                    if (location != null) {
                        continuation.resume(location)
                    } else {
                        continuation.resumeWithException(Exception("Unable to get current location"))
                    }
                }
            }

            locationManager.delegate = locationDelegate
        }
    }

    private class LocationDelegate : NSObject(), CLLocationManagerDelegateProtocol {

        var onLocationUpdate: ((Location?) -> Unit)? = null

        override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
            Napier.d(
                tag = TAG,
                message = "locationManager, didUpdateLocations: $didUpdateLocations"
            )
            didUpdateLocations.firstOrNull()?.let {
                val location = it as CLLocation
                location.coordinate.useContents {
                    onLocationUpdate?.invoke(Location(latitude, longitude))
                }
            }
        }

        override fun locationManager(manager: CLLocationManager, didFailWithError: NSError) {
            Napier.e(tag = TAG, message = "locationManager, didFailWithError: $didFailWithError")
            onLocationUpdate?.invoke(null)
        }
    }

    companion object {
        private const val TAG = "LocationClient"
    }
}