package com.github.arhi23.photosocnet.data.repositories

import androidx.paging.PagingSource
import com.github.arhi23.photosocnet.api.network.psnApi.FeedNetworkApi
import com.github.arhi23.photosocnet.core.RepositoryError.SpecificError
import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.Result.Failure
import com.github.arhi23.photosocnet.core.Result.Success
import com.github.arhi23.photosocnet.core.repo.IPhotoRepository
import com.github.arhi23.photosocnet.data.NetworkResponseNorm
import com.github.arhi23.photosocnet.data.daos.MediaItemDao
import com.github.arhi23.photosocnet.data.daos.RemoteKeyDao
import com.github.arhi23.photosocnet.core.entities.MediaItem
import com.github.arhi23.photosocnet.data.mediaItemsNet
import com.github.arhi23.photosocnet.data.userItemNetList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotoRepository @Inject constructor(
  private val feedNetworkApi: FeedNetworkApi,
  private val mediaItemDao: MediaItemDao,
  private val networkResponseNorm: NetworkResponseNorm,
  private val remoteKeyDao: RemoteKeyDao,
) : IPhotoRepository {


  override fun pagingSourceTimeline(): PagingSource<Int, MediaItem> = mediaItemDao.pagingSourceTimeline()

  override suspend fun requestPhotos(loadKey: String, refresh: Boolean) {
    val response = feedNetworkApi.getFeedMedia(loadKey, 25)
    CoroutineScope(Dispatchers.IO).launch {
      val loadKey = loadKey.toInt()
      if (response is Success) {
        if (refresh) {
          mediaItemDao.deleteAll()
        }
        remoteKeyDao.insertOrReplace(RemoteKeyEnt("media", response.data.batchId))
        networkResponseNorm.mapsaveUsers(response.data.userItems)
        networkResponseNorm.mapsaveMedias(response.data.items)
        networkResponseNorm.mapsaveComments(response.data.comments)
        networkResponseNorm.mapsaveEmotes(response.data.emotes)
        networkResponseNorm.mapsaveLikes(response.data.likes)
      }
    }
  }

  override suspend fun getMediaItem(mediaId: String): Result<MediaItem> {
    val response = feedNetworkApi.getMediaItem(mediaId)
    return when (response) {
      is Success -> {
        networkResponseNorm.mapsaveUsers(response.data.userItems)
        networkResponseNorm.mapsaveMedias(response.data.items)
        networkResponseNorm.mapsaveComments(response.data.comments)
        networkResponseNorm.mapsaveEmotes(response.data.emotes)
        networkResponseNorm.mapsaveLikes(response.data.likes)
        val media = mediaItemDao.findById(mediaId)
        return Success(media)
      }

      is Failure -> Failure(response.data)
      else -> Failure(SpecificError("PhotoRepository getMediaItem error"))
    }
  }

  override suspend fun getRefreshKey(): String? {
    return remoteKeyDao.getByRequest("media")?.lastKey
  }

}