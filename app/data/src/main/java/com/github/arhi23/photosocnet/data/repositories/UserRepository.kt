package com.github.arhi23.photosocnet.data.repositories

import androidx.paging.PagingSource
import com.github.arhi23.photosocnet.api.network.psnApi.UserNetworkApi
import com.github.arhi23.photosocnet.core.RepositoryError.SpecificError
import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.Result.Failure
import com.github.arhi23.photosocnet.core.Result.Success
import com.github.arhi23.photosocnet.core.repo.IUserRepository
import com.github.arhi23.photosocnet.data.NetworkResponseNorm
import com.github.arhi23.photosocnet.data.daos.RemoteKeyDao
import com.github.arhi23.photosocnet.data.daos.UserItemDao
import com.github.arhi23.photosocnet.core.entities.RemoteKeyEnt
import com.github.arhi23.photosocnet.core.entities.UserItemEnt
import javax.inject.Inject

class UserRepository @Inject constructor(
  private val userItemDao: UserItemDao,
  private val userNetworkApi: UserNetworkApi,
  private val networkResponseNorm: NetworkResponseNorm,
  private val remoteKeyDao: RemoteKeyDao,
) : IUserRepository {

  override fun pagingSourceTimeline(): PagingSource<Int, UserItemEnt> = userItemDao.pagingSourceTimeline()

  override suspend fun getUser(userId: String): Result<UserItemEnt> {
    val response = userNetworkApi.getUser(userId)
    return when (response) {
      is Success -> {
        val user = response.data.user
        networkResponseNorm.mapsaveUser(user)
        return Success(userItemDao.findById(userId))
      }

      is Failure -> Failure(response.data)
      else -> Failure(SpecificError("UserRepository getUser error"))
    }
  }

  override suspend fun getRefreshKey(): String? {
    return remoteKeyDao.getByRequest("followers")?.lastKey
  }

  override suspend fun requestUsers(loadKey: String, refresh: Boolean) {
    val response = userNetworkApi.getUsers(loadKey, 25)
    when (response) {
      is Success -> {
        if (refresh) {
          userItemDao.deleteAll()
        }
        val loadKeyInt = loadKey.toInt()
        val result = response.data
        val users = result.users
        if (loadKeyInt >= users.size) {
          remoteKeyDao.insertOrReplace(RemoteKeyEnt("followers", null))
          return
        }
        remoteKeyDao.insertOrReplace(RemoteKeyEnt("followers", result.batchId))
        networkResponseNorm.mapsaveUsers(users)
      }

      is Failure -> {

      }

      else -> {

      }
    }
  }
  override suspend fun getOwner(): Result<UserItemEnt> {
    val response =  userNetworkApi.getOwner()
    return when (response) {
      is Success -> {
        networkResponseNorm.mapsaveUser(response.data.user)
        return Success(userItemDao.getOwner())
      }

      is Failure -> Failure(response.data)
      else -> Failure(SpecificError("UserRepository getOwner error"))
    }
  }

  override suspend fun saveOwner(name: String, description: String): Result<UserItemEnt> {
    val response = userNetworkApi.saveOwner(name, description)
    return when (response) {
      is Success -> {
        val user = response.data.user
        networkResponseNorm.mapsaveUser(user)
        Success(userItemDao.getOwner())
      }

      is Failure -> Failure(response.data)
      else -> Failure(SpecificError("UserRepository saveOwner error"))
    }
  }
}
