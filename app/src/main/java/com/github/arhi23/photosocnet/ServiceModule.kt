package com.github.arhi23.photosocnet

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
//
//@InstallIn(SingletonComponent::class)
//@Module
//class ServiceModule {
//  @Named("app")
//  @Provides
//  @Singleton
//  fun provideAppPreferences(
//    @ApplicationContext context: Context,
//  ): SharedPreferences {
//    return context.getSharedPreferences("app", Context.MODE_PRIVATE)
//  }
//}