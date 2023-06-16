package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationRequest(
  @field:Json(name = "login") val login: String,
  @field:Json(name = "password") val password: String
)