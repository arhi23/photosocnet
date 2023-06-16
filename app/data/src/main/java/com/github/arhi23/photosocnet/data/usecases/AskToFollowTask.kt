package com.github.arhi23.photosocnet.data.usecases

import com.github.arhi23.photosocnet.core.RepositoryError.SpecificError
import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.Result.Failure
import javax.inject.Inject

class AskToFollowTask @Inject constructor() {

  suspend operator fun invoke(params: Params): Result<Void> {
    TODO("Not yet implemented")
    return Failure(SpecificError())
  }

  data class Params(val message: String, val userId: String)
}

