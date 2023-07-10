package com.github.arhi23.photosocnet.core.repo

interface ISocialInteractionRepository {
  suspend fun like(contentId: String)

  suspend fun unLike(likeId: String)

  suspend fun comment(contentId: String, text: String)

  suspend fun editComment(commentId: String, text: String)

  suspend fun deleteComment(commentId: String)

  suspend fun emote(contentId: String, emote: String)

  suspend fun deleteEmote(emoteId: String)
}