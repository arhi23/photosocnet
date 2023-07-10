package com.github.arhi23.photosocnet.core.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.repo.IUserRepository
import javax.inject.Inject

class UpdateAccountTask @Inject constructor(val userRepository: IUserRepository) {

  suspend operator fun invoke(params: Params): Result<Void> {
    TODO("Not yet implemented")
  }

  data class Params(val name: String, val description: String)
}

