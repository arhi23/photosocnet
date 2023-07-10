package com.github.arhi23.photosocnet.core.repo

interface IAuthenticationTokenManager {
  fun saveToken(token: String)
  fun getToken(): String?
  fun clear()
  fun isAuthenticated(): Boolean
}