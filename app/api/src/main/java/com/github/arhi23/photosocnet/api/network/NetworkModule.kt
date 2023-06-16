package com.github.arhi23.photosocnet.api.network

import com.github.arhi23.photosocnet.api.network.psnApi.AppNetworkApi
import com.github.arhi23.photosocnet.api.network.psnApi.AuthNetworkApi
import com.github.arhi23.photosocnet.api.network.psnApi.FeedNetworkApi
import com.github.arhi23.photosocnet.api.network.psnApi.FollowersNetworkApi
import com.github.arhi23.photosocnet.api.network.psnApi.FollowingNetworkApi
import com.github.arhi23.photosocnet.api.network.psnApi.NonAuthorizedNetworkApi
import com.github.arhi23.photosocnet.api.network.psnApi.PSN_API_URI
import com.github.arhi23.photosocnet.api.network.psnApi.UserNetworkApi
import com.github.arhi23.photosocnet.api.network.services.AuthorizationRetrofitService
import com.github.arhi23.photosocnet.api.network.services.RetrofitService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.MINUTES
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
open class NetworkModule {

  open fun getBaseUrl(): HttpUrl = PSN_API_URI.toHttpUrl()

  @Provides
  fun providesRetrofitBuilder(moshi: Moshi) = Builder()
    .baseUrl(getBaseUrl())
    .addConverterFactory(MoshiConverterFactory.create(moshi))

  @Provides
  @Singleton
  fun providesRetrofitService(
    @AuthorizedClient server: Retrofit
  ): RetrofitService = server.create(RetrofitService::class.java)

  @Provides
  @Singleton
  fun providesFeedNetworkApi(networkApi: AppNetworkApi
  ): FeedNetworkApi = networkApi

  @Provides
  @Singleton
  fun providesFollowersNetworkApi(networkApi: AppNetworkApi
  ): FollowersNetworkApi = networkApi

  @Provides
  @Singleton
  fun providesFollowingNetworkApi(networkApi: AppNetworkApi
  ): FollowingNetworkApi = networkApi

  @Provides
  @Singleton
  fun providesUserApi(networkApi: AppNetworkApi
  ): UserNetworkApi = networkApi

  @Provides
  @Singleton
  fun providesAuthNetworkApi(networkApi: NonAuthorizedNetworkApi
  ): AuthNetworkApi = networkApi

  @Provides
  @Singleton
  fun providesAuthorizationService(
    @NonAuthorizedClient server: Retrofit
  ): AuthorizationRetrofitService = server.create(AuthorizationRetrofitService::class.java)

  @AuthorizedClient
  @Provides
  fun providesRetrofitClient(
    @AuthorizedClient okHttpClient: OkHttpClient,
    builder: Builder
  ): Retrofit = builder
    .client(okHttpClient)
    .build()

  @NonAuthorizedClient
  @Provides
  fun providesAuthorizationRetrofitClient(
    @NonAuthorizedClient okHttpClient: OkHttpClient,
    builder: Builder
  ): Retrofit = builder
    .client(okHttpClient)
    .build()

  @NonAuthorizedClient
  @Provides
  fun providesNonAuthorizedOkHttpClient(
    okHttpBuilder: OkHttpClient.Builder
  ) = okHttpBuilder
    .build()

  @Provides
  fun providesDefaultOkHttpBuilder(
    @DataInterceptor dataInterceptor: Interceptor
  ) = OkHttpClient.Builder()
    .connectionPool(ConnectionPool(3, 2, MINUTES))
    .dispatcher(
      Dispatcher().apply {
        maxRequestsPerHost = 3
      },
    )
    .connectTimeout(20, SECONDS)
    .readTimeout(20, SECONDS)
    .writeTimeout(20, SECONDS)
    .addInterceptor(dataInterceptor)

  @AuthorizedClient
  @Provides
  fun providesAuthorizedOkHttpClient(
    okHttpBuilder: OkHttpClient.Builder,
    @AuthInterceptor authInterceptor: Interceptor,
  ) = okHttpBuilder
    .addInterceptor(authInterceptor)
    .build()

  @Provides
  @AuthInterceptor
  fun providesAuthInterceptor(apiCredentials: ApiCredentials) = Interceptor { chain ->
    val request = chain.request().newBuilder()
      .addHeader("key", apiCredentials.generatedId)
      .build()
    chain.proceed(request)
  }

  @Provides
  @DataInterceptor
  fun providesDataAuthInterceptor(apiCredentials: ApiCredentials) = Interceptor { chain ->
    val request = chain.request().newBuilder()
      .addHeader("model", "phoneModel")
      .addHeader("uid", "uid")
      .addHeader("phoneLocale", "phoneLocale")
      .addHeader("timeZone", "timeZone")
      .addHeader("screenDensity", "screenDensity")
      .addHeader("ipAddress", "ipAddress")
      .addHeader("macAddress", "macAddress")
      .build()
    chain.proceed(request)
  }

  @Provides
  @Singleton
  fun providesSerializer() = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthorizedClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NonAuthorizedClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DataInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptor