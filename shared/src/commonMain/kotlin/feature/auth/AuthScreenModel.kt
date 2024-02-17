package feature.auth

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import di.CommonFactoryProvider
import domain.repository.AuthRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthScreenModel(
    private val authRepository: AuthRepository,
) : StateScreenModel<AuthUiState>(initialState = AuthUiState()) {

    init {
        observeAuth()
    }

    private fun observeAuth() {
        authRepository.getLogin()
            .onEach { login ->
                mutableState.update { it.copy(login = login) }
            }
            .catch { }
            .launchIn(screenModelScope)
    }

    fun onSaveClick(login: String) {
        screenModelScope.launch {
            authRepository.saveLogin(login)
        }
    }

    companion object {
        fun create(): AuthScreenModel {
            return AuthScreenModel(
                authRepository = CommonFactoryProvider.commonFactory.authRepository
            )
        }
    }
}