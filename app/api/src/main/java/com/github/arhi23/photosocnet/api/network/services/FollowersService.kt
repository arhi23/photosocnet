package com.github.arhi23.photosocnet.api.network.services

import com.github.arhi23.photosocnet.api.network.model.FollowersListResponse
import com.github.arhi23.photosocnet.api.network.model.FollowingRequest
import com.github.arhi23.photosocnet.api.network.model.RemoveFollowerRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FollowersService {

  @GET(FOLLOWERS_PATH) suspend fun getFollowers(@Query("userId") userId: String, @Query("from") from: String, @Query("count") count: Int = 50): Response<FollowersListResponse>

  @POST(REMOVE_FOLLOWER_PATH) suspend fun removeFollower(@Body request: RemoveFollowerRequest): Response<ResponseBody>

  @POST(REQUEST_FOLLOWING_PATH) suspend fun requestFollowing(@Body request: FollowingRequest): Response<ResponseBody>

}
const val FOLLOWERS_PATH = "followers"
const val REMOVE_FOLLOWER_PATH = "$FOLLOWERS_PATH/remove"
const val REQUEST_FOLLOWING_PATH = "$FOLLOWERS_PATH/request"