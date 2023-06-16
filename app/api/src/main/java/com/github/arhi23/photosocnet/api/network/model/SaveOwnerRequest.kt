package com.github.arhi23.photosocnet.api.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SaveOwnerRequest(
  @field:Json(name = "name") val name: String,
  @field:Json(name = "description") val description: String
)