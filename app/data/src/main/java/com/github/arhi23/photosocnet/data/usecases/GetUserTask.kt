package com.github.arhi23.photosocnet.data.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.data.entities.UserItemEnt
import com.github.arhi23.photosocnet.data.repositories.UserRepository
import javax.inject.Inject

class GetUserTask @Inject constructor(
  private val userRepository: UserRepository
) {

  suspend operator fun invoke(params: Params): Result<UserItemEnt> {
    return userRepository.getUser(params.userId)
  }

  data class Params(val userId: String)
}