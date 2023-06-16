package com.github.arhi23.photosocnet.data.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.data.repositories.UserRepository
import javax.inject.Inject

class UpdateAccountTask @Inject constructor(val userRepository: UserRepository) {

  suspend operator fun invoke(params: Params): Result<Void> {
    TODO("Not yet implemented")
  }

  data class Params(val name: String, val description: String)
}

