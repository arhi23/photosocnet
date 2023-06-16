package com.github.arhi23.photosocnet.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
open class CoreModule {

  @Provides
  fun providesAppDispatchers(): AppDispatchers =
    AppDispatchers(main = Dispatchers.Main, io = Dispatchers.IO)
}