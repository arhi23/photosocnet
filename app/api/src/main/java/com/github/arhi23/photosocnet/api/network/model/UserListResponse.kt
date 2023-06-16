package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserListResponse(
  @field:Json(name = "user_items") val userItems: List<UserItemNet>,
  @field:Json(name = "batch_id") val batchId: String
)