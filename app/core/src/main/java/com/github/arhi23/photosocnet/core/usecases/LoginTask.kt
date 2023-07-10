package com.github.arhi23.photosocnet.core.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.repo.IAuthenticationRepository
import javax.inject.Inject

class LoginTask @Inject constructor(private val authRepo: IAuthenticationRepository) {

  suspend operator fun invoke(params: Params): Result<String> {

//    delay(3000)
    return authRepo.authenticate(params.login, params.password)
  }

  data class Params(val login: String, val password: String)
}