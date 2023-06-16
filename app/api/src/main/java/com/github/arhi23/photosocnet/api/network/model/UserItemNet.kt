package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserItemNet(
  @field:Json(name = "id") val id: String,
  @field:Json(name = "name") val name: String,
  @field:Json(name = "is_owner") val isOwner: Boolean,
  @field:Json(name = "description") val description: String,
  @field:Json(name = "avatar_url") val avatarUrl: String
)