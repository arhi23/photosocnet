package com.github.arhi23.photosocnet.api.network.services

import com.github.arhi23.photosocnet.api.network.model.MediaItemResponse
import com.github.arhi23.photosocnet.api.network.model.MediaListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MediaRetrofitService {

  @GET(MEDIA_PATH) suspend fun getFeed(@Query("from") from: String, @Query("count") count: Int = 50): Response<MediaListResponse>

  @GET(MEDIA_PATH) suspend fun getMedia(
    @Query("userId") userId: String,
    @Query("from") from: String,
    @Query("count") count: Int = 50
  ): Response<MediaListResponse>

  @GET(MEDIA_PATH) suspend fun getMediaItem(
    @Query("mediaId") mediaId: String
  ): Response<MediaItemResponse>

  @GET(LIKED_MEDIA_PATH) suspend fun getLiked(id: String, from: String, count: Int = 50): Response<MediaListResponse>

  @GET(NOT_LIKED_MEDIA_PATH) suspend fun getNotLiked(id: String, from: String, count: Int = 50): Response<MediaListResponse>
}

const val MEDIA_PATH = "media"
const val LIKED_MEDIA_PATH = "$MEDIA_PATH/liked"
const val NOT_LIKED_MEDIA_PATH = "$MEDIA_PATH/not_liked"