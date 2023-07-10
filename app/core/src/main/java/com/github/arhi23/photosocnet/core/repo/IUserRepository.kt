package com.github.arhi23.photosocnet.core.repo

import androidx.paging.PagingSource
import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.entities.UserItemEnt

interface IUserRepository {
  fun pagingSourceTimeline(): PagingSource<Int, UserItemEnt>

  suspend fun getUser(userId: String): Result<UserItemEnt>

  suspend fun getRefreshKey(): String?

  suspend fun requestUsers(loadKey: String, refresh: Boolean)

  suspend fun getOwner(): Result<UserItemEnt>

  suspend fun saveOwner(name: String, description: String): Result<UserItemEnt>
}