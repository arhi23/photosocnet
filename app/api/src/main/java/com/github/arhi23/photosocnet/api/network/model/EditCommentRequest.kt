package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EditCommentRequest(
  @field:Json(name = "comment_id") val commentId: String,
  @field:Json(name = "text") val text: String
)