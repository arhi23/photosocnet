package com.github.arhi23.photosocnet.data.repositories

import com.github.arhi23.photosocnet.api.network.psnApi.FollowersNetworkApi
import com.github.arhi23.photosocnet.core.RepositoryError.SpecificError
import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.Result.Failure
import com.github.arhi23.photosocnet.core.Result.Success
import com.github.arhi23.photosocnet.core.repo.IFollowersRepository
import com.github.arhi23.photosocnet.data.daos.FollowersDao
import javax.inject.Inject

class FollowersRepository @Inject constructor(
  private val followersNetworkApi: FollowersNetworkApi,
  private val followersDao: FollowersDao
) : IFollowersRepository {

  override suspend fun askToFollow(userId: String): Result<Any?> {
    return try {
      followersNetworkApi.requestFollowing(userId)
      Success(null)
    } catch (e: Error) {
      Failure(SpecificError(""))
    }
  }

  override suspend fun removeFollower(userId: String): Result<Any?> {
    return try {
      followersNetworkApi.removeFollower(userId)
//      followersDao.remove(userId)
      Success(null)
    } catch (e: Error) {
      Failure(SpecificError(""))
    }
  }
}