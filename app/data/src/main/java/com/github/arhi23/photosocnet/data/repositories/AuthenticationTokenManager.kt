package com.github.arhi23.photosocnet.data.repositories

import android.content.SharedPreferences
import javax.inject.Inject

private const val AUTH_KEY = "authKey"

class AuthenticationTokenManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

  fun saveToken(token: String) {
    sharedPreferences.edit().putString(AUTH_KEY, token).apply()
  }

  fun getToken(): String? {
    return sharedPreferences.getString(AUTH_KEY, "")
  }

  fun clear() {
    sharedPreferences.edit().clear().apply()
  }

  fun isAuthenticated(): Boolean {
    return !getToken().isNullOrBlank()
  }
}