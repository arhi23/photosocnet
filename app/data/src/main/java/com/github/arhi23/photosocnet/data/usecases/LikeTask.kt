package com.github.arhi23.photosocnet.data.usecases

import com.github.arhi23.photosocnet.core.Result
import javax.inject.Inject

class LikeTask @Inject constructor() {

  suspend operator fun invoke(params: Params): Result<Void> {
    TODO("Not yet implemented")
  }

  data class Params(val likeId: String)
}