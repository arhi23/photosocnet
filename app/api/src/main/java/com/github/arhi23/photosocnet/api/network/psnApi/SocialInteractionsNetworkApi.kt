package com.github.arhi23.photosocnet.api.network.psnApi

import com.github.arhi23.photosocnet.core.Result
import okhttp3.ResponseBody

interface SocialInteractionsNetworkApi {
  suspend fun like(contentId: String): Result<ResponseBody>

  suspend fun unLike(likeId: String): Result<ResponseBody>

  suspend fun comment(contentId: String, text: String): Result<ResponseBody>

  suspend fun editComment(commentId: String, text: String): Result<ResponseBody>

  suspend fun deleteComment(commentId: String): Result<ResponseBody>

  suspend fun emote(contentId: String, emote: String): Result<ResponseBody>

  suspend fun deleteEmote(emoteId: String): Result<ResponseBody>
}