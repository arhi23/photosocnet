package com.github.arhi23.photosocnet.core.usecases

import com.github.arhi23.photosocnet.core.Result
import javax.inject.Inject

class SendCommentTask @Inject constructor() {

  suspend operator fun invoke(params: Params): Result<Void> {
    TODO("Not yet implemented")
  }

  data class Params(val contentId: String, val text: String)
}