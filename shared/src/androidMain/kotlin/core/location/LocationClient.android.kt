package core.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import domain.model.Location
import kotlinx.coroutines.tasks.await

actual class LocationClient(context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }
    private val cancellationTokenSource by lazy { CancellationTokenSource() }

    @SuppressLint("MissingPermission")
    actual suspend fun getCurrentLocation(): Location? {
        val location: android.location.Location? = fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            cancellationTokenSource.token
        ).await()

        return location?.let {
            Location(
                latitude = location.latitude,
                longitude = location.longitude
            )
        }
    }
}