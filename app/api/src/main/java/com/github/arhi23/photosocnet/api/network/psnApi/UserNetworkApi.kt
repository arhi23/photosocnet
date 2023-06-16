package com.github.arhi23.photosocnet.api.network.psnApi

import com.github.arhi23.photosocnet.api.network.model.GetUserResponse
import com.github.arhi23.photosocnet.api.network.model.GetUsersResponse
import com.github.arhi23.photosocnet.api.network.model.SaveOwnerResponse
import com.github.arhi23.photosocnet.core.Result

interface UserNetworkApi {

  suspend fun getOwner(): Result<GetUserResponse>

  suspend fun saveOwner(name: String, description: String): Result<SaveOwnerResponse>

  suspend fun getUser(userId: String): Result<GetUserResponse>

  suspend fun getUsers(from: String, count: Int): Result<GetUsersResponse>
}