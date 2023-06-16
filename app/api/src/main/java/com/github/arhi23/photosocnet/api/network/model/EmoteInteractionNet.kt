package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmoteInteractionNet(
  @field:Json(name = "id") val id: String,
  @field:Json(name = "content") val content: String,
  @field:Json(name = "can_edit") val canEdit: Boolean,
  @field:Json(name = "count") val count: Int,
  @field:Json(name = "creator_id") val creatorId: String,
  @field:Json(name = "by_owner") val byOwner: Boolean,
  @field:Json(name = "content_id") val contentId: String
)