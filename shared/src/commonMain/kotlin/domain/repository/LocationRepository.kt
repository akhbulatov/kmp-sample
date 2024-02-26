package domain.repository

import domain.model.Location

interface LocationRepository {
    suspend fun getCurrentLocation(): Location?
}