package com.github.arhi23.photosocnet.core.sources

import androidx.paging.PagingData
import com.github.arhi23.photosocnet.core.entities.UserItemEnt
import kotlinx.coroutines.flow.Flow

interface FollowersRemoteDataSource {
  fun getFollowers(): Flow<PagingData<UserItemEnt>>
}