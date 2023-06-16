package com.github.arhi23.photosocnet.data.repositories

import com.github.arhi23.photosocnet.api.network.psnApi.SocialInteractionsNetworkApi

class SocialInteractionRepository(
  private val socialInteractionApi: SocialInteractionsNetworkApi
) {

  suspend fun like(contentId: String) {
    socialInteractionApi.like(contentId)
  }

  suspend fun unLike(likeId: String) {
    socialInteractionApi.unLike(likeId)
  }

  suspend fun comment(contentId: String, text: String) {
    socialInteractionApi.comment(contentId, text)
  }

  suspend fun editComment(commentId: String, text: String) {
    socialInteractionApi.editComment(commentId, text)
  }

  suspend fun deleteComment(commentId: String) {
    socialInteractionApi.deleteComment(commentId)
  }

  suspend fun emote(contentId: String, emote: String) {
    socialInteractionApi.emote(contentId, emote)
  }

  suspend fun deleteEmote(emoteId: String) {
    socialInteractionApi.deleteEmote(emoteId)
  }
}