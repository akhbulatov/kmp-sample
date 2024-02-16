package domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getLogin(): Flow<String?>
    suspend fun saveLogin(login: String)
}