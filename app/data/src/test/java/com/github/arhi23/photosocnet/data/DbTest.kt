package com.github.arhi23.photosocnet.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object TestRoomDatabaseModule {

  @Provides
  fun provideDatabase(@ApplicationContext context: Context): AppDbRoom {
    return Room.inMemoryDatabaseBuilder(context, AppDbRoom::class.java)
      .allowMainThreadQueries()
      .build()
  }
}
