package com.github.arhi23.photosocnet.followers

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import coil.network.HttpException
import com.github.arhi23.photosocnet.data.entities.UserItemEnt
import com.github.arhi23.photosocnet.data.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject


const val NETWORK_PAGE_SIZE = 25
const val FIRST_BATCH_ID = "0"

interface FollowersRemoteDataSource {
  fun getFollowers(): Flow<PagingData<UserItemEnt>>
}

@OptIn(ExperimentalPagingApi::class)
class FollowersDataSource @Inject constructor(
  private val userRepository: UserRepository
) : FollowersRemoteDataSource {

  override fun getFollowers(): Flow<PagingData<UserItemEnt>> {
    return Pager(
      config = PagingConfig(
        pageSize = NETWORK_PAGE_SIZE,
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
  private val userRepository: UserRepository
) : RemoteMediator<Int, UserItemEnt>() {

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, UserItemEnt>
  ): MediatorResult {
    return try {
      val loadKey = when (loadType) {
        LoadType.REFRESH -> FIRST_BATCH_ID
        LoadType.PREPEND -> return MediatorResult.Success(true)
        LoadType.APPEND -> userRepository.getRefreshKey() ?: return MediatorResult.Success(true)
      }
      userRepository.requestUsers(loadKey, loadType == LoadType.REFRESH)
      MediatorResult.Success(false)
    } catch (e: IOException) {
      MediatorResult.Error(e)
    } catch (e: HttpException) {
      MediatorResult.Error(e)
    }
  }
}