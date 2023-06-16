package com.github.arhi23.photosocnet.api

import com.github.arhi23.photosocnet.api.network.NetworkModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.HttpUrl

@Module
@TestInstallIn(
  components = [SingletonComponent::class],
  replaces = [NetworkModule::class]
)
class MockNetworkModule: NetworkModule() {
  override fun getBaseUrl(): HttpUrl = MockServer.server.url("/")
}