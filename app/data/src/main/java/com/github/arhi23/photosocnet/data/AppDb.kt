package com.github.arhi23.photosocnet.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.arhi23.photosocnet.data.daos.CommentDao
import com.github.arhi23.photosocnet.data.daos.EmoteDao
import com.github.arhi23.photosocnet.data.daos.LikeDao
import com.github.arhi23.photosocnet.data.daos.MediaItemDao
import com.github.arhi23.photosocnet.data.daos.RemoteKeyDao
import com.github.arhi23.photosocnet.data.daos.UserItemDao
import com.github.arhi23.photosocnet.data.entities.CommentInfoEnt
import com.github.arhi23.photosocnet.data.entities.EmoteInfoEnt
import com.github.arhi23.photosocnet.data.entities.LikeInfoEnt
import com.github.arhi23.photosocnet.data.entities.MediaInfoEnt
import com.github.arhi23.photosocnet.data.entities.RemoteKeyEnt
import com.github.arhi23.photosocnet.data.entities.UserItemEnt

interface AppDb {
  abstract fun userDao(): UserItemDao
  abstract fun commentDao(): CommentDao
  abstract fun likeDao(): LikeDao
  abstract fun emoteDao(): EmoteDao
  abstract fun mediaItemDao(): MediaItemDao
  abstract fun remoteKeyDao(): RemoteKeyDao
}


@Database(
  entities = [UserItemEnt::class, CommentInfoEnt::class, LikeInfoEnt::class, EmoteInfoEnt::class, MediaInfoEnt::class, RemoteKeyEnt::class],
  version = 1
)
abstract class AppDbRoom : RoomDatabase(), AppDb