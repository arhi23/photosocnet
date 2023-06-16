package com.github.arhi23.photosocnet.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.github.arhi23.photosocnet.data.entities.MediaItem
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject


const val NETWORK_PAGE_SIZE = 25
const val FIRST_BATCH_ID = "0"

interface MediaRemoteDataSource {
  fun getMedia(): Flow<PagingData<MediaItem>>
}

@OptIn(ExperimentalPagingApi::class)
class MediaDataSource @Inject constructor(
  private val photoRepository: PhotoRepository
) : MediaRemoteDataSource {

  override fun getMedia(): Flow<PagingData<MediaItem>> {
    return Pager(
      config = PagingConfig(
        pageSize = NETWORK_PAGE_SIZE,
        enablePlaceholders = false
      ),
      initialKey = 0,
      remoteMediator = MediaMediator(
        photoRepository = photoRepository
      )
    )
    {
      photoRepository.pagingSourceTimeline()
    }.flow
  }
}

@OptIn(ExperimentalPagingApi::class)
class MediaMediator(
  private val photoRepository: PhotoRepository
) : RemoteMediator<Int, MediaItem>() {

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, MediaItem>
  ): MediatorResult {
    return try {
      val loadKey = when (loadType) {
        LoadType.REFRESH -> FIRST_BATCH_ID
        LoadType.PREPEND -> return MediatorResult.Success(true)
        LoadType.APPEND -> photoRepository.getRefreshKey() ?: return MediatorResult.Success(true)
      }
      photoRepository.requestPhotos(loadKey, loadType == LoadType.REFRESH)
      MediatorResult.Success(false)
    } catch (e: IOException) {
      MediatorResult.Error(e)
    }
  }
}