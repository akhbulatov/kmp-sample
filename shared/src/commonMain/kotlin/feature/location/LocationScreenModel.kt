package feature.location

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import di.CommonFactoryProvider
import domain.repository.LocationRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationScreenModel(
    private val locationRepository: LocationRepository
) : StateScreenModel<LocationUiState>(LocationUiState()) {

    init {
        observeCurrentLocation()
    }

    private fun observeCurrentLocation() {
        screenModelScope.launch {
            try {
                val location = locationRepository.getCurrentLocation()
                mutableState.update { it.copy(currentLocation = location) }
            } catch (e: Exception) {
                Napier.e("Failed to observe current location: $e")
            }
        }
    }

    companion object {
        fun create(): LocationScreenModel {
            return LocationScreenModel(
                locationRepository = CommonFactoryProvider.commonFactory.locationRepository
            )
        }
    }
}