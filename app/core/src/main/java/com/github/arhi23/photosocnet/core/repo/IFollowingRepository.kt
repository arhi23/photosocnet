package com.github.arhi23.photosocnet.core.repo

import com.github.arhi23.photosocnet.core.Result

interface IFollowingRepository {
  suspend fun follow(userId: String): Result<Any?>

  suspend fun unFollow(userId: String): Result<Any?>
}