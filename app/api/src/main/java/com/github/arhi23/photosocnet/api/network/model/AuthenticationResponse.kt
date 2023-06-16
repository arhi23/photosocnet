package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationResponse(
  @field:Json(name = "authentication_key") val authenticationKey: String,
  @field:Json(name = "valid_until") val validUntil: String
)