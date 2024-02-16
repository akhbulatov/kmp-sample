package org.example.kmpsample.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.kmpsample.KmpSampleApp

class AuthViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    init {
        observeAuth()
    }

    private fun observeAuth() {
        authRepository.getLogin()
            .onEach { login ->
                _uiState.update { it.copy(login = login) }
            }
            .catch { }
            .launchIn(viewModelScope)
    }

    fun onSaveClick(login: String) {
        viewModelScope.launch {
            authRepository.saveLogin(login)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(
                    authRepository = KmpSampleApp.commonFactory.authRepository
                ) as T
            }
        }
    }
}