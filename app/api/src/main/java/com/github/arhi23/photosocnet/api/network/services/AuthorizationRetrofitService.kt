package com.github.arhi23.photosocnet.api.network.services

import com.github.arhi23.photosocnet.api.network.model.AuthenticationRequest
import com.github.arhi23.photosocnet.api.network.model.AuthenticationResponse
import com.github.arhi23.photosocnet.api.network.model.ExchangeTokenRequest
import com.github.arhi23.photosocnet.api.network.model.TokenExchangeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationRetrofitService {

  @POST("users/authenticate")
  suspend fun authenticate(@Body request: AuthenticationRequest): Response<AuthenticationResponse>

  @POST("users/exchangeToken")
  suspend fun exchangeToken(@Body request: ExchangeTokenRequest): Response<TokenExchangeResponse>
}