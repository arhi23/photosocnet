package com.github.arhi23.photosocnet.api.network.psnApi

import com.github.arhi23.photosocnet.api.network.model.AuthenticationResponse
import com.github.arhi23.photosocnet.api.network.model.TokenExchangeResponse
import com.github.arhi23.photosocnet.core.Result

interface AuthNetworkApi {
  suspend fun authenticate(login: String, password: String): Result<AuthenticationResponse>

  suspend fun exchangeToken(oldToken: String): Result<TokenExchangeResponse>
}