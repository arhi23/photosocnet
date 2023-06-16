package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaItemNet(
  @field:Json(name = "media_url") val mediaUrl: String,
  @field:Json(name = "thumb_url") val thumbUrl: String,
  @field:Json(name = "content_id") val contentId: String,
  @field:Json(name = "creator_id") val creatorId: String,
  @field:Json(name = "type") val type: String,
  @field:Json(name = "by_owner") val byOwner: Boolean,
  @field:Json(name = "can_edit") val canEdit: Boolean,
  @field:Json(name = "created_at") val createdAt: Long,
  @field:Json(name = "comment_count") val commentCount: Int,
  @field:Json(name = "like_count") val likeCount: Int,
)