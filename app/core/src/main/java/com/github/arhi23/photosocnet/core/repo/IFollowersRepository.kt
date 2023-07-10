package com.github.arhi23.photosocnet.core.repo

import com.github.arhi23.photosocnet.core.Result

interface IFollowersRepository {
  suspend fun askToFollow(userId: String): Result<Any?>

  suspend fun removeFollower(userId: String): Result<Any?>
}