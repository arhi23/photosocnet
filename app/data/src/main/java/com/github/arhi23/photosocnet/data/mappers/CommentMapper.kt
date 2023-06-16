package com.github.arhi23.photosocnet.data.mappers

import com.github.arhi23.photosocnet.api.network.model.CommentInteractionNet
import com.github.arhi23.photosocnet.data.entities.CommentInfoEnt
import javax.inject.Inject

class CommentMapper @Inject constructor() : Mapper<CommentInteractionNet, CommentInfoEnt> {
  override suspend fun map(from: CommentInteractionNet): CommentInfoEnt {
    return with(from) {
      CommentInfoEnt(
        id = id,
        creatorId = owner,
        contentId = contentId,
        content = content,
        canEdit = canEdit,
        byOwner = byOwner,
        commentCount = commentCount,
        likeCount = likeCount,
        createdAt = createdAt
      )
    }
  }
}