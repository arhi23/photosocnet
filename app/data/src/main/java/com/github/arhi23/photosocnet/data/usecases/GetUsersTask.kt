package com.github.arhi23.photosocnet.data.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.Result.Success
import com.github.arhi23.photosocnet.data.repositories.UserRepository
import javax.inject.Inject

class GetUsersTask @Inject constructor(private val userRepository: UserRepository) {

  suspend operator fun invoke(params: Params): Result<Any?> {
    userRepository.requestUsers(params.loadKey, params.refresh)
    return Success(null)
  }

  data class Params(val loadKey: String, val refresh: Boolean)
}