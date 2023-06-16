package com.github.arhi23.photosocnet.data.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.data.entities.UserItemEnt
import com.github.arhi23.photosocnet.data.repositories.UserRepository
import javax.inject.Inject

class SaveOwnerTask  @Inject constructor(private val userRepository: UserRepository) {

  suspend operator fun invoke(params: Params): Result<UserItemEnt> {
    return userRepository.saveOwner(params.name, params.description)
  }

  data class Params(val name: String, val description: String)
}