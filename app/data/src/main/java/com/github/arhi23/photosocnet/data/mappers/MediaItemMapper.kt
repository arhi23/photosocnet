package com.github.arhi23.photosocnet.data.mappers

import com.github.arhi23.photosocnet.api.network.model.MediaItemNet
import com.github.arhi23.photosocnet.core.entities.MediaInfoEnt
import javax.inject.Inject

class MediaItemMapper @Inject constructor(): Mapper<MediaItemNet, MediaInfoEnt> {
  override suspend fun map(from: MediaItemNet): MediaInfoEnt {
    return with(from) {
      MediaInfoEnt(
        contentId = contentId,
        mediaUrl = mediaUrl,
        thumbUrl = thumbUrl,
        byOwner = byOwner,
        type = type,
        creatorId = creatorId,
        createdAt = createdAt,
        commentCount = commentCount,
        likeCount = likeCount)
    }
  }
}
