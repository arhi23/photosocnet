package com.github.arhi23.photosocnet.data.mappers

import com.github.arhi23.photosocnet.api.network.model.LikeInteractionNet
import com.github.arhi23.photosocnet.core.entities.LikeInfoEnt
import javax.inject.Inject

class LikeMapper @Inject constructor() : Mapper<LikeInteractionNet, LikeInfoEnt> {
  override suspend fun map(from: LikeInteractionNet): LikeInfoEnt {
    return with(from) {
      LikeInfoEnt(
        id = id,
        contentId = contentId,
        canEdit = canEdit,
        creatorId = creatorId,
        byOwner = byOwner
      )
    }
  }
}