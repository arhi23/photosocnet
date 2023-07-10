package com.github.arhi23.photosocnet.data.repositories

import com.github.arhi23.photosocnet.api.network.psnApi.SocialInteractionsNetworkApi
import com.github.arhi23.photosocnet.core.repo.ISocialInteractionRepository

class SocialInteractionRepository(
  private val socialInteractionApi: SocialInteractionsNetworkApi
) : ISocialInteractionRepository {

  override suspend fun like(contentId: String) {
    socialInteractionApi.like(contentId)
  }

  override suspend fun unLike(likeId: String) {
    socialInteractionApi.unLike(likeId)
  }

  override suspend fun comment(contentId: String, text: String) {
    socialInteractionApi.comment(contentId, text)
  }

  override suspend fun editComment(commentId: String, text: String) {
    socialInteractionApi.editComment(commentId, text)
  }

  override suspend fun deleteComment(commentId: String) {
    socialInteractionApi.deleteComment(commentId)
  }

  override suspend fun emote(contentId: String, emote: String) {
    socialInteractionApi.emote(contentId, emote)
  }

  override suspend fun deleteEmote(emoteId: String) {
    socialInteractionApi.deleteEmote(emoteId)
  }
}