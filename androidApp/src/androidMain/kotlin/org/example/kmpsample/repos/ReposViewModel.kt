package org.example.kmpsample.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import domain.repository.RepoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.kmpsample.KmpSampleApp

class ReposViewModel(
    private val repoRepository: RepoRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReposUiState())
    val uiState: StateFlow<ReposUiState> = _uiState

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(emptyProgress = true) }
                val repos = repoRepository.getRepos()
                _uiState.update { it.copy(repos = repos) }
            } catch (e: Exception) {
                _uiState.update { it.copy(emptyError = e.message) }
            } finally {
                _uiState.update { it.copy(emptyProgress = false) }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                @Suppress("UNCHECKED_CAST")
                return ReposViewModel(
                    repoRepository = KmpSampleApp.commonFactory.repoRepository
                ) as T
            }
        }
    }
}