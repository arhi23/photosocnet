package com.github.arhi23.photosocnet.core.repo

import androidx.paging.PagingSource
import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.entities.MediaItem

interface IPhotoRepository {
  fun pagingSourceTimeline(): PagingSource<Int, MediaItem>

  suspend fun requestPhotos(loadKey: String, refresh: Boolean)

  suspend fun getMediaItem(mediaId: String): Result<MediaItem>

  suspend fun getRefreshKey(): String?
}