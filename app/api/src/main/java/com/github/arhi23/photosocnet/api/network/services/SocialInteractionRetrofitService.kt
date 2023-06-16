package com.github.arhi23.photosocnet.api.network.services

import com.github.arhi23.photosocnet.api.network.model.CommentRequest
import com.github.arhi23.photosocnet.api.network.model.DeleteCommentRequest
import com.github.arhi23.photosocnet.api.network.model.DeleteEmoteRequest
import com.github.arhi23.photosocnet.api.network.model.DeleteLikeRequest
import com.github.arhi23.photosocnet.api.network.model.EditCommentRequest
import com.github.arhi23.photosocnet.api.network.model.EmoteRequest
import com.github.arhi23.photosocnet.api.network.model.LikeRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.POST

interface SocialInteractionRetrofitService {

  @POST(LIKE_PATH ) fun like(request: LikeRequest): Response<ResponseBody>

  @POST(UNLIKE_PATH ) fun unLike(request: DeleteLikeRequest): Response<ResponseBody>

  @POST(COMMENT_PATH ) fun comment(request: CommentRequest): Response<ResponseBody>

  @POST(EDIT_COMMENT_PATH) fun editComment(request: EditCommentRequest): Response<ResponseBody>

  @POST(DELETE_COMMENT_PATH) fun deleteComment(request: DeleteCommentRequest): Response<ResponseBody>

  @POST(EMOTE_PATH) fun emote(request: EmoteRequest): Response<ResponseBody>

  @POST(DELETE_EMOTE_PATH) fun deleteEmote(request: DeleteEmoteRequest): Response<ResponseBody>
}

const val SOCIAL_INTERACTIONS = "social_interaction"
const val LIKE_PATH  = "$SOCIAL_INTERACTIONS/like"
const val UNLIKE_PATH  = "$SOCIAL_INTERACTIONS/un_like"
const val COMMENT_PATH  = "$SOCIAL_INTERACTIONS/comment"
const val EDIT_COMMENT_PATH  = "$SOCIAL_INTERACTIONS/edit_comment"
const val DELETE_COMMENT_PATH  = "$SOCIAL_INTERACTIONS/delete_comment"
const val EMOTE_PATH  = "$SOCIAL_INTERACTIONS/emote"
const val DELETE_EMOTE_PATH  = "$SOCIAL_INTERACTIONS/delete_emote"