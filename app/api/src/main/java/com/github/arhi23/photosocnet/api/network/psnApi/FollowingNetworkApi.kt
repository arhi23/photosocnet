package com.github.arhi23.photosocnet.api.network.psnApi

import com.github.arhi23.photosocnet.core.Result
import okhttp3.ResponseBody

interface FollowingNetworkApi {
  suspend fun getFollowing(userId: String, from: String, count: Int): Result<ResponseBody>

  suspend fun follow(userId: String): Result<ResponseBody>

  suspend fun unfollow(userId: String): Result<ResponseBody>
}