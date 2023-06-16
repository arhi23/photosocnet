package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LikeInteractionNet(
  @field:Json(name = "id") val id: String,
  @field:Json(name = "can_edit") val canEdit: Boolean,
  @field:Json(name = "by_owner") val byOwner: Boolean,
  @field:Json(name = "content_id") val contentId: String,
  @field:Json(name = "creator_id") val creatorId: String
)