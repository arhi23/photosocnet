package com.github.arhi23.photosocnet.data.repositories

import com.github.arhi23.photosocnet.api.network.psnApi.FollowingNetworkApi
import com.github.arhi23.photosocnet.core.RepositoryError.SpecificError
import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.Result.Failure
import com.github.arhi23.photosocnet.core.Result.Success
import com.github.arhi23.photosocnet.data.daos.FollowDao
import javax.inject.Inject

class FollowingRepository @Inject constructor(
  private val followingNetworkApi: FollowingNetworkApi,
  private val followDao: FollowDao
) {

  suspend fun follow(userId: String): Result<Any?> {
    return try {
      val result = followingNetworkApi.follow(userId)
//      followDao.insert(userId)
      Success(null)
    } catch (e: Error) {
      Failure(SpecificError(""))
    }
  }

  suspend fun unFollow(userId: String): Result<Any?> {
    return try {
      val result = followingNetworkApi.unfollow(userId)
      followDao.delete(userId)
      Success(null)
    } catch (e: Error) {
      Failure(SpecificError(""))
    }
  }
}