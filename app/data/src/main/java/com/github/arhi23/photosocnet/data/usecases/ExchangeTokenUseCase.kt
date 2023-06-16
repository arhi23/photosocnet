package com.github.arhi23.photosocnet.data.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.data.repositories.AuthenticationRepository
import javax.inject.Inject

class ExchangeTokenUseCase
@Inject constructor(
  private val authenticationRepository: AuthenticationRepository
) {

  suspend operator fun invoke(): Result<String> {
    return authenticationRepository.exchangeToken()
  }
}
