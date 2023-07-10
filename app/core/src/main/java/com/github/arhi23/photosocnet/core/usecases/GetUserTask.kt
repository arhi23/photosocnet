package com.github.arhi23.photosocnet.core.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.entities.UserItemEnt
import com.github.arhi23.photosocnet.core.repo.IUserRepository
import javax.inject.Inject

class GetUserTask @Inject constructor(
  private val userRepository: IUserRepository
) {

  suspend operator fun invoke(params: Params): Result<UserItemEnt> {
    return userRepository.getUser(params.userId)
  }

  data class Params(val userId: String)
}