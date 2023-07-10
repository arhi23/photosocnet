package com.github.arhi23.photosocnet.core.sources

import androidx.paging.PagingData
import com.github.arhi23.photosocnet.core.entities.MediaItem
import kotlinx.coroutines.flow.Flow

interface MediaRemoteDataSource {
  fun getMedia(): Flow<PagingData<MediaItem>>
}