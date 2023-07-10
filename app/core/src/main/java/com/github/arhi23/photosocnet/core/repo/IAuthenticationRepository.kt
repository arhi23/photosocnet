package com.github.arhi23.photosocnet.core.repo

import com.github.arhi23.photosocnet.core.Result

interface IAuthenticationRepository {
  fun isUserAuthenticated(): Boolean
  fun getAuthToken(): String?
  fun clearAuthenticationInfo()

  suspend fun authenticate(login: String, password: String): Result<String>
  fun saveAuthToken(token: String)

  suspend fun exchangeToken(): Result<String>
}