package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetUserResponse(
  @field:Json(name = "user") val user: UserItemNet
)

@JsonClass(generateAdapter = true)
data class GetUsersResponse(
  @field:Json(name = "batch_id") val batchId: String,
  @field:Json(name = "users") val users: List<UserItemNet>
)