package com.github.arhi23.photosocnet.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.github.arhi23.photosocnet.core.entities.UserItemEnt
import com.github.arhi23.photosocnet.core.sources.FollowersRemoteDataSource
import com.github.arhi23.photosocnet.core.repo.IUserRepository
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

const val FOLLOWERS_NETWORK_PAGE_SIZE = 25
const val FOLLOWERS_FIRST_BATCH_ID = "0"

@OptIn(ExperimentalPagingApi::class)
class FollowersDataSource @Inject constructor(
  private val userRepository: IUserRepository
) : FollowersRemoteDataSource {

  override fun getFollowers(): Flow<PagingData<UserItemEnt>> {
    return Pager(
      config = PagingConfig(
        pageSize = FOLLOWERS_NETWORK_PAGE_SIZE,
        enablePlaceholders = false
      ),
      initialKey = 0,
      remoteMediator = FollowersMediator(
        userRepository = userRepository
      )
    )
    {
      userRepository.pagingSourceTimeline()
    }.flow
  }
}

@OptIn(ExperimentalPagingApi::class)
class FollowersMediator(
  private val userRepository: IUserRepository
) : RemoteMediator<Int, UserItemEnt>() {

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, UserItemEnt>
  ): MediatorResult {
    return try {
      val loadKey = when (loadType) {
        LoadType.REFRESH -> FOLLOWERS_FIRST_BATCH_ID
        LoadType.PREPEND -> return MediatorResult.Success(true)
        LoadType.APPEND -> userRepository.getRefreshKey() ?: return MediatorResult.Success(true)
      }
      userRepository.requestUsers(loadKey, loadType == LoadType.REFRESH)
      MediatorResult.Success(false)
    } catch (e: IOException) {
      MediatorResult.Error(e)
    }
  }
}