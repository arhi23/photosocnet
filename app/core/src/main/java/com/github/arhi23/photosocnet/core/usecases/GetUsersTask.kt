package com.github.arhi23.photosocnet.core.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.Result.Success
import com.github.arhi23.photosocnet.core.repo.IUserRepository
import javax.inject.Inject

class GetUsersTask @Inject constructor(private val userRepository: IUserRepository) {

  suspend operator fun invoke(params: Params): Result<Any?> {
    userRepository.requestUsers(params.loadKey, params.refresh)
    return Success(null)
  }

  data class Params(val loadKey: String, val refresh: Boolean)
}