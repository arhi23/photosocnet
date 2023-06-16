package com.github.arhi23.photosocnet.api.network.psnApi

import com.github.arhi23.photosocnet.api.network.model.AuthenticationRequest
import com.github.arhi23.photosocnet.api.network.model.AuthenticationResponse
import com.github.arhi23.photosocnet.api.network.model.ExchangeTokenRequest
import com.github.arhi23.photosocnet.api.network.model.TokenExchangeResponse
import com.github.arhi23.photosocnet.api.network.services.AuthorizationRetrofitService
import com.github.arhi23.photosocnet.core.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NonAuthorizedNetworkApi @Inject constructor(private val retrofitService: AuthorizationRetrofitService) :
  AuthNetworkApi {

  override suspend fun authenticate(login: String, password: String): Result<AuthenticationResponse> {
    return handleRequest { retrofitService.authenticate(AuthenticationRequest(login, password)) }
  }

  override suspend fun exchangeToken(oldToken: String): Result<TokenExchangeResponse> {
    return handleRequest { retrofitService.exchangeToken(ExchangeTokenRequest(oldToken)) }
  }
}