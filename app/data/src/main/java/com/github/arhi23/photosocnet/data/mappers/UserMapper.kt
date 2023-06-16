package com.github.arhi23.photosocnet.data.mappers

import com.github.arhi23.photosocnet.api.network.model.UserItemNet
import com.github.arhi23.photosocnet.data.entities.UserItemEnt
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<UserItemNet, UserItemEnt> {
  override suspend fun map(from: UserItemNet): UserItemEnt {
    return with(from) {
      UserItemEnt(
        name = name,
        isOwner = isOwner,
        avatarUrl = avatarUrl,
        uid = id,
        serverId = id,
        description = description
      )
    }
  }
}