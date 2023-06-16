package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FollowersListResponse(
  @field:Json(name = "items") val items: List<MediaItemNet>,
  @field:Json(name = "likes") val likes: List<LikeInteractionNet>,
  @field:Json(name = "comments") val comments: List<CommentInteractionNet>,
  @field:Json(name = "emotes") val emotes: List<EmoteInteractionNet>,
  @field:Json(name = "user_items") val userItems: List<UserItemNet>,
  @field:Json(name = "batch_id") val batchId: String
)