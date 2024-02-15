package org.example.kmpsample.repos

import domain.model.Repo

data class ReposUiState(
    val emptyProgress: Boolean = false,
    val emptyError: String? = null,
    val repos: List<Repo> = emptyList()
)