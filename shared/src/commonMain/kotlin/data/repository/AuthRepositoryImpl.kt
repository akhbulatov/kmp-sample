package data.repository

import core.preferences.AppPreferences
import domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val appPreferences: AppPreferences
) : AuthRepository{

    override fun getLogin(): Flow<String?> {
        return appPreferences.getLogin()
    }

    override suspend fun saveLogin(login: String) {
        appPreferences.saveLogin(login)
    }
}