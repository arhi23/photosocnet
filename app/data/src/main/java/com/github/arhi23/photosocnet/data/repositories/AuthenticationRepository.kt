package com.github.arhi23.photosocnet.data.repositories

import com.github.arhi23.photosocnet.api.network.psnApi.AuthNetworkApi
import com.github.arhi23.photosocnet.core.RepositoryError.SpecificError
import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.Result.Failure
import com.github.arhi23.photosocnet.core.Result.Success
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
  private val authNetworkApi: AuthNetworkApi,
  private val tokenManager: AuthenticationTokenManager,
) {

  fun isUserAuthenticated(): Boolean {
    return !getAuthToken().isNullOrBlank()
  }

  fun getAuthToken(): String? {
    return tokenManager.getToken()
  }

  fun clearAuthenticationInfo() {
    tokenManager.clear()
  }

  suspend fun authenticate(login: String, password: String): Result<String> {
    val response = authNetworkApi.authenticate(login, password)
    return when (response) {
      is Success -> {
        val newToken = response.data.authenticationKey
        saveAuthToken(newToken)
        Success(newToken)
      }
      is Failure -> Failure(response.data)
    }
  }

  private fun saveAuthToken(token: String) {
    tokenManager.saveToken(token)
  }

  suspend fun exchangeToken(): Result<String> {
    val oldToken =
      getAuthToken() ?: return Failure(data = SpecificError("exchangeToken old token is null"))
    val response = authNetworkApi.exchangeToken(oldToken)
    return when (response) {
      is Success -> {
        val newToken = response.data.token
        saveAuthToken(newToken)
        Success(newToken)
      }
      is Failure -> Failure(response.data)
      else -> Failure(SpecificError("AuthenticationRepository exchangeToken error"))
    }
  }
}
