package com.github.arhi23.photosocnet.core.usecases

import com.github.arhi23.photosocnet.core.Result
import com.github.arhi23.photosocnet.core.entities.UserItemEnt
import com.github.arhi23.photosocnet.core.repo.IUserRepository
import javax.inject.Inject

class GetOwnerTask @Inject constructor(private val userRepository: IUserRepository) {

  suspend operator fun invoke(): Result<UserItemEnt> {
    return userRepository.getOwner()
  }
}
