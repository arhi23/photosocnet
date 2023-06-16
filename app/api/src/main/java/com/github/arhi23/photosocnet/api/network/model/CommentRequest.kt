package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentRequest(
  @field:Json(name = "content_id") val contentId: String,
  @field:Json(name = "text") val text: String
)