package com.github.arhi23.photosocnet.api.network.psnApi

import com.github.arhi23.photosocnet.api.network.model.FollowersListResponse
import com.github.arhi23.photosocnet.core.Result
import okhttp3.ResponseBody

interface FollowersNetworkApi {
  suspend fun getFollowers(userId: String, from: String, count: Int): Result<FollowersListResponse>

  suspend fun removeFollower(userId: String): Result<ResponseBody>

  suspend fun requestFollowing(userId: String): Result<ResponseBody>
}