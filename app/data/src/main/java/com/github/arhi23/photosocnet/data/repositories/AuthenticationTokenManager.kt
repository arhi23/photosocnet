package com.github.arhi23.photosocnet.data.repositories

import android.content.SharedPreferences
import com.github.arhi23.photosocnet.core.repo.IAuthenticationTokenManager
import javax.inject.Inject

private const val AUTH_KEY = "authKey"



class AuthenticationTokenManager @Inject constructor(private val sharedPreferences: SharedPreferences) :
  IAuthenticationTokenManager {

  override fun saveToken(token: String) {
    sharedPreferences.edit().putString(AUTH_KEY, token).apply()
  }

  override fun getToken(): String? {
    return sharedPreferences.getString(AUTH_KEY, "")
  }

  override fun clear() {
    sharedPreferences.edit().clear().apply()
  }

  override fun isAuthenticated(): Boolean {
    return !getToken().isNullOrBlank()
  }
}