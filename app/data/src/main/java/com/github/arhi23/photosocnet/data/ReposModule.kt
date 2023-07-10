package com.github.arhi23.photosocnet.data

import com.github.arhi23.photosocnet.core.sources.FollowersRemoteDataSource
import com.github.arhi23.photosocnet.core.repo.IAuthenticationRepository
import com.github.arhi23.photosocnet.core.repo.IFollowersRepository
import com.github.arhi23.photosocnet.core.repo.IFollowingRepository
import com.github.arhi23.photosocnet.core.repo.IPhotoRepository
import com.github.arhi23.photosocnet.core.repo.ISocialInteractionRepository
import com.github.arhi23.photosocnet.core.repo.IUserRepository
import com.github.arhi23.photosocnet.core.sources.MediaRemoteDataSource
import com.github.arhi23.photosocnet.data.repositories.AuthenticationRepository
import com.github.arhi23.photosocnet.data.repositories.FollowersRepository
import com.github.arhi23.photosocnet.data.repositories.FollowingRepository
import com.github.arhi23.photosocnet.data.repositories.MediaDataSource
import com.github.arhi23.photosocnet.data.repositories.PhotoRepository
import com.github.arhi23.photosocnet.data.repositories.SocialInteractionRepository
import com.github.arhi23.photosocnet.data.repositories.UserRepository
import com.github.arhi23.photosocnet.data.repositories.FollowersDataSource
import dagger.Module
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface ReposModule {

  @Binds
  fun bindAuthenticationRepository(repo: AuthenticationRepository): IAuthenticationRepository

  @Binds
  fun bindFollowersRepository(repo: FollowersRepository): IFollowersRepository

  @Binds
  fun bindFollowingRepository(repo: FollowingRepository): IFollowingRepository

  @Binds
  fun bindPhotoRepository(repo: PhotoRepository): IPhotoRepository

  @Binds
  fun bindSocialInteractionRepository(repo: SocialInteractionRepository): ISocialInteractionRepository

  @Binds
  fun bindUserRepository(repo: UserRepository): IUserRepository

  @Binds
  fun bindMediaDataSource(source: MediaDataSource): MediaRemoteDataSource

  @Binds
  fun bindFollowersDataSource(source: FollowersDataSource): FollowersRemoteDataSource
}