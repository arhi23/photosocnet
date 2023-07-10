package com.github.arhi23.photosocnet.core.usecases

import com.github.arhi23.photosocnet.core.RepositoryError.SpecificError
import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.Result.Failure
import javax.inject.Inject

class DeleteEmoteTask @Inject constructor() {

  suspend operator fun invoke(params: Params): Result<Any?> {
    TODO("Not yet implemented")
    return Failure(SpecificError())
  }

  data class Params(val emoteId: String)
}
