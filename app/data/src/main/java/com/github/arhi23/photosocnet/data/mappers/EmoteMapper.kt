package com.github.arhi23.photosocnet.data.mappers

import com.github.arhi23.photosocnet.api.network.model.EmoteInteractionNet
import com.github.arhi23.photosocnet.data.entities.EmoteInfoEnt
import javax.inject.Inject

class EmoteMapper @Inject constructor() : Mapper<EmoteInteractionNet, EmoteInfoEnt> {
  override suspend fun map(from: EmoteInteractionNet): EmoteInfoEnt {
    return with(from) {
      EmoteInfoEnt(
        id = id,
        contentId = contentId,
        content = content,
        canEdit = canEdit,
        creatorId = creatorId,
        count = count,
        byOwner = byOwner
      )
    }
  }
}