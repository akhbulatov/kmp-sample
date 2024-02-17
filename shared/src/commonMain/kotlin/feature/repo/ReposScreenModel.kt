package feature.repo

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import di.CommonFactoryProvider
import domain.repository.RepoRepository
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReposScreenModel(
    private val repoRepository: RepoRepository,
) : StateScreenModel<ReposUiState>(ReposUiState()) {

    init {
        loadRepos()
    }

    private fun loadRepos() {
        screenModelScope.launch {
            try {
                mutableState.update { it.copy(emptyProgress = true) }
                val repos = repoRepository.getRepos()
                mutableState.update { it.copy(repos = repos) }
            } catch (e: Exception) {
                mutableState.update { it.copy(emptyError = e.message) }
            } finally {
                mutableState.update { it.copy(emptyProgress = false) }
            }
        }
    }

    companion object {
        fun create(): ReposScreenModel {
            return ReposScreenModel(
                repoRepository = CommonFactoryProvider.commonFactory.repoRepository
            )
        }
    }
}