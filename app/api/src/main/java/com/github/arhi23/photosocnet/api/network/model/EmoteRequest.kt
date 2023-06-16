package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmoteRequest(
  @field:Json(name = "content_id") val contentId: String,
  @field:Json(name = "emote") val emote: String
)