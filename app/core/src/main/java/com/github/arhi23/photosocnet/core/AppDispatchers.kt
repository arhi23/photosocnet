package com.github.arhi23.photosocnet.core

import kotlinx.coroutines.CoroutineDispatcher

data class AppDispatchers(
  val main: CoroutineDispatcher,
  val io: CoroutineDispatcher
)