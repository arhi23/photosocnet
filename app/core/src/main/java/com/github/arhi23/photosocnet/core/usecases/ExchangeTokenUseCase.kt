package com.github.arhi23.photosocnet.core.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.repo.IAuthenticationRepository
import javax.inject.Inject

class ExchangeTokenUseCase
@Inject constructor(
  private val authenticationRepository: IAuthenticationRepository
) {

  suspend operator fun invoke(): Result<String> {
    return authenticationRepository.exchangeToken()
  }
}
