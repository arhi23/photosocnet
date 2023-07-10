package com.github.arhi23.photosocnet.core.usecases

import com.github.arhi23.photosocnet.core.Result
import javax.inject.Inject

class RemoveFollowerTask @Inject constructor() {

  suspend operator fun invoke(params: Params): Result<Void> {
    TODO("Not yet implemented")
  }

  data class Params(val userId: String)
}