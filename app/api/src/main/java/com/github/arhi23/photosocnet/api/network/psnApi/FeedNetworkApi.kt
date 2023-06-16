package com.github.arhi23.photosocnet.api.network.psnApi

import com.github.arhi23.photosocnet.api.network.model.MediaItemResponse
import com.github.arhi23.photosocnet.api.network.model.MediaListResponse
import com.github.arhi23.photosocnet.core.Result

interface FeedNetworkApi {
  suspend fun getFeedMedia(from: String, count: Int): Result<MediaListResponse>

  suspend fun getMedia(userId: String, from: String, count: Int): Result<MediaListResponse>

  suspend fun getMediaItem(mediaId: String): Result<MediaItemResponse>
}