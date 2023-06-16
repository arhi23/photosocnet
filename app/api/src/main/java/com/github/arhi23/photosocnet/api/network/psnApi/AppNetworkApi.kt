package com.github.arhi23.photosocnet.api.network.psnApi

import com.github.arhi23.photosocnet.api.network.model.CommentRequest
import com.github.arhi23.photosocnet.api.network.model.DeleteCommentRequest
import com.github.arhi23.photosocnet.api.network.model.DeleteLikeRequest
import com.github.arhi23.photosocnet.api.network.model.EditCommentRequest
import com.github.arhi23.photosocnet.api.network.model.EmoteRequest
import com.github.arhi23.photosocnet.api.network.model.FollowRequest
import com.github.arhi23.photosocnet.api.network.model.FollowersListResponse
import com.github.arhi23.photosocnet.api.network.model.FollowingRequest
import com.github.arhi23.photosocnet.api.network.model.GetUserResponse
import com.github.arhi23.photosocnet.api.network.model.GetUsersResponse
import com.github.arhi23.photosocnet.api.network.model.LikeRequest
import com.github.arhi23.photosocnet.api.network.model.MediaItemResponse
import com.github.arhi23.photosocnet.api.network.model.MediaListResponse
import com.github.arhi23.photosocnet.api.network.model.RemoveFollowerRequest
import com.github.arhi23.photosocnet.api.network.model.SaveOwnerRequest
import com.github.arhi23.photosocnet.api.network.model.SaveOwnerResponse
import com.github.arhi23.photosocnet.api.network.model.UnfollowRequest
import com.github.arhi23.photosocnet.api.network.services.RetrofitService
import com.github.arhi23.photosocnet.core.RepositoryError.BadRequest
import com.github.arhi23.photosocnet.core.RepositoryError.Forbidden
import com.github.arhi23.photosocnet.core.RepositoryError.InternalServerError
import com.github.arhi23.photosocnet.core.RepositoryError.LargePayload
import com.github.arhi23.photosocnet.core.RepositoryError.NotFound
import com.github.arhi23.photosocnet.core.RepositoryError.ServerFailure
import com.github.arhi23.photosocnet.core.RepositoryError.ServiceUnavailable
import com.github.arhi23.photosocnet.core.RepositoryError.SpecificError
import com.github.arhi23.photosocnet.core.RepositoryError.Unauthorized
import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.Result.Failure
import com.github.arhi23.photosocnet.core.Result.Success
import com.squareup.moshi.JsonDataException
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNetworkApi @Inject constructor(private val retrofitService: RetrofitService) :
  FeedNetworkApi, UserNetworkApi, FollowersNetworkApi, FollowingNetworkApi,
  SocialInteractionsNetworkApi {

  override suspend fun getFeedMedia(from: String, count: Int): Result<MediaListResponse> {
    return handleRequest { retrofitService.getFeed(from, count) }
  }

  override suspend fun getMedia(
    userId: String,
    from: String,
    count: Int
  ): Result<MediaListResponse> {
    return handleRequest { retrofitService.getMedia(userId, from, count) }
  }

  override suspend fun getMediaItem(mediaId: String): Result<MediaItemResponse> {
    return handleRequest { retrofitService.getMediaItem(mediaId) }
  }

  override suspend fun getUser(userId: String): Result<GetUserResponse> {
    return handleRequest { retrofitService.getUser(userId) }
  }

  override suspend fun getOwner(): Result<GetUserResponse> {
    return handleRequest { retrofitService.getOwner() }
  }

  override suspend fun saveOwner(name: String, description: String): Result<SaveOwnerResponse> {
    return handleRequest { retrofitService.saveOwner(SaveOwnerRequest(name, description)) }
  }

  override suspend fun getUsers(from: String, count: Int): Result<GetUsersResponse> {
    return handleRequest { retrofitService.getUsers(from, count) }
  }

  override suspend fun getFollowers(
    userId: String,
    from: String,
    count: Int
  ): Result<FollowersListResponse> {
    return handleRequest { retrofitService.getFollowers(userId, from, count) }
  }

  override suspend fun getFollowing(
    userId: String,
    from: String,
    count: Int
  ): Result<ResponseBody> {
    return handleRequest { retrofitService.getFollowing(userId, from, count) }
  }

  override suspend fun follow(userId: String): Result<ResponseBody> {
    return handleRequest { retrofitService.follow(FollowRequest(userId)) }
  }

  override suspend fun unfollow(userId: String): Result<ResponseBody> {
    return handleRequest { retrofitService.unfollow(UnfollowRequest(userId)) }
  }

  override suspend fun removeFollower(userId: String): Result<ResponseBody> {
    return handleRequest { retrofitService.removeFollower(RemoveFollowerRequest(userId)) }
  }

  override suspend fun requestFollowing(userId: String): Result<ResponseBody> {
    return handleRequest { retrofitService.requestFollowing(FollowingRequest(userId)) }
  }

  override suspend fun like(contentId: String): Result<ResponseBody> {
    return handleRequest { retrofitService.like(LikeRequest(contentId)) }
  }

  override suspend fun unLike(likeId: String): Result<ResponseBody> {
    return handleRequest { retrofitService.unLike(DeleteLikeRequest(likeId)) }
  }

  override suspend fun comment(contentId: String, text: String): Result<ResponseBody> {
    return handleRequest { retrofitService.comment(CommentRequest(contentId, text)) }
  }

  override suspend fun editComment(commentId: String, text: String): Result<ResponseBody> {
    return handleRequest { retrofitService.editComment(EditCommentRequest(commentId, text)) }
  }

  override suspend fun deleteComment(commentId: String): Result<ResponseBody> {
    return handleRequest { retrofitService.deleteComment(DeleteCommentRequest(commentId)) }
  }

  override suspend fun emote(contentId: String, emote: String): Result<ResponseBody> {
    return handleRequest { retrofitService.emote(EmoteRequest(contentId, emote)) }
  }

  override suspend fun deleteEmote(emoteId: String): Result<ResponseBody> {
    return handleRequest { retrofitService.unfollow(UnfollowRequest(emoteId)) }
  }
}

suspend fun <T> handleRequest(func: suspend () -> Response<T>): Result<T> {
  return try {
    val result = func.invoke()
    when {
      result.isSuccessful -> Success(result.body()!!)
      else -> {
        val error = when (result.code()) {
          400 -> BadRequest()
          401 -> Unauthorized()
          403 -> Forbidden()
          404 -> NotFound()
          413 -> LargePayload()
          500 -> InternalServerError()
          503 -> ServiceUnavailable()
          else -> ServerFailure
        }
        Failure(error)
      }
    }
  } catch (e: HttpException) {
    return Failure(SpecificError(e.message ?: ""))
  } catch (e: JsonDataException) {
    return Failure(SpecificError(e.message ?: ""))
  }
}