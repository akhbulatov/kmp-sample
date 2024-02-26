package core.location

import domain.model.Location

expect class LocationClient {
    suspend fun getCurrentLocation(): Location?
}