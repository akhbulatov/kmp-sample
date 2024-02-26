package data.repository

import core.location.LocationClient
import domain.model.Location
import domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationClient: LocationClient
) : LocationRepository {

    override suspend fun getCurrentLocation(): Location? {
        return locationClient.getCurrentLocation()
    }
}