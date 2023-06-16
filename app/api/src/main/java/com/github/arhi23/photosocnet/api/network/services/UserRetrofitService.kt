package com.github.arhi23.photosocnet.api.network.services

import com.github.arhi23.photosocnet.api.network.model.GetUserResponse
import com.github.arhi23.photosocnet.api.network.model.GetUsersResponse
import com.github.arhi23.photosocnet.api.network.model.SaveOwnerRequest
import com.github.arhi23.photosocnet.api.network.model.SaveOwnerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserRetrofitService {

  @GET(GET_USERS_PATH) suspend fun getUsers(from: String, count: Int = 50): Response<GetUsersResponse>

  @GET(GET_USER_PATH) suspend fun getUser(@Path("user_id") userId: String): Response<GetUserResponse>

  @GET(GET_OWNER_PATH) suspend fun getOwner(): Response<GetUserResponse>

  @POST(SAVE_OWNER_PATH) suspend fun saveOwner(@Body saveOwnerRequest: SaveOwnerRequest): Response<SaveOwnerResponse>
}

const val GET_USERS_PATH = "users"
const val GET_USER_PATH = "$GET_USERS_PATH/{user_id}"
const val GET_OWNER_PATH = "$GET_USERS_PATH/owner"
const val SAVE_OWNER_PATH = "$GET_USERS_PATH/saveOwner"