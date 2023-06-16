package com.github.arhi23.photosocnet.data.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.data.entities.MediaItem
import com.github.arhi23.photosocnet.data.repositories.PhotoRepository
import javax.inject.Inject

class GetMediaItemTask @Inject constructor(
  private val photoRepository: PhotoRepository
) {

  suspend operator fun invoke(params: Params): Result<MediaItem> {
    return photoRepository.getMediaItem(params.mediaId)
  }

  data class Params(val mediaId: String)
}