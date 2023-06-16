package com.github.arhi23.photosocnet.api.network.services

import com.github.arhi23.photosocnet.api.network.model.FollowRequest
import com.github.arhi23.photosocnet.api.network.model.UnfollowRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FollowingService {

  @GET(FOLLOWING_PATH) suspend fun getFollowing(
    @Query("userId") userId: String,
    @Query("from") from: String,
    @Query("count") count: Int = 50
  ): Response<ResponseBody>

  @POST(FOLLOW_PATH) suspend fun follow(@Body request: FollowRequest): Response<ResponseBody>

  @POST(UNFOLLOW_PATH) suspend fun unfollow(@Body request: UnfollowRequest): Response<ResponseBody>

}
const val FOLLOWING_PATH = "following"
const val FOLLOW_PATH = "$FOLLOWING_PATH/follow"
const val UNFOLLOW_PATH = "$FOLLOWING_PATH/unfollow"