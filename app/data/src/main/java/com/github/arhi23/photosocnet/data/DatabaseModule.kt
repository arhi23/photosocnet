package com.github.arhi23.photosocnet.data

import android.content.Context
import androidx.room.Room
import com.github.arhi23.photosocnet.data.daos.CommentDao
import com.github.arhi23.photosocnet.data.daos.EmoteDao
import com.github.arhi23.photosocnet.data.daos.LikeDao
import com.github.arhi23.photosocnet.data.daos.MediaItemDao
import com.github.arhi23.photosocnet.data.daos.RemoteKeyDao
import com.github.arhi23.photosocnet.data.daos.UserItemDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

  @Provides
  fun provideMediaItemDao(appDatabase: AppDb): MediaItemDao = appDatabase.mediaItemDao()

  @Provides
  fun provideRemoteKeyDao(appDatabase: AppDb): RemoteKeyDao = appDatabase.remoteKeyDao()

  @Provides
  fun provideUserItemDao(appDatabase: AppDb): UserItemDao = appDatabase.userDao()

  @Provides
  fun provideCommentDao(appDatabase: AppDb): CommentDao = appDatabase.commentDao()

  @Provides
  fun provideLikeDao(appDatabase: AppDb): LikeDao = appDatabase.likeDao()

  @Provides
  fun provideEmoteDao(appDatabase: AppDb): EmoteDao = appDatabase.emoteDao()

}

@InstallIn(SingletonComponent::class)
@Module
object RoomDbModule {
  @Provides
  @Singleton
  fun provideAppDatabase(@ApplicationContext appContext: Context): AppDbRoom =
    Room.databaseBuilder(
      appContext,
      AppDbRoom::class.java,
      "dream.db"
    ).build()
}

@InstallIn(SingletonComponent::class)
@Module
abstract class DbModuleBinds {
  @Binds
  abstract fun bindAppDb(db: AppDbRoom): AppDb
}