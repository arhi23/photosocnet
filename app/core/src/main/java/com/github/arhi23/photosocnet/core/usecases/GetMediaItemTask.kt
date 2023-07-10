package com.github.arhi23.photosocnet.core.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.entities.MediaItem
import com.github.arhi23.photosocnet.core.repo.IPhotoRepository
import javax.inject.Inject

class GetMediaItemTask @Inject constructor(
  private val photoRepository: IPhotoRepository
) {

  suspend operator fun invoke(params: Params): Result<MediaItem> {
    return photoRepository.getMediaItem(params.mediaId)
  }

  data class Params(val mediaId: String)
}