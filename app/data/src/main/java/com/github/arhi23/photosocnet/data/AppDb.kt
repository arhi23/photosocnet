package com.github.arhi23.photosocnet.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.arhi23.photosocnet.data.daos.CommentDao
import com.github.arhi23.photosocnet.data.daos.EmoteDao
import com.github.arhi23.photosocnet.data.daos.LikeDao
import com.github.arhi23.photosocnet.data.daos.MediaItemDao
import com.github.arhi23.photosocnet.data.daos.RemoteKeyDao
import com.github.arhi23.photosocnet.data.daos.UserItemDao
import com.github.arhi23.photosocnet.core.entities.CommentInfoEnt
import com.github.arhi23.photosocnet.core.entities.EmoteInfoEnt
import com.github.arhi23.photosocnet.core.entities.LikeInfoEnt
import com.github.arhi23.photosocnet.core.entities.MediaInfoEnt
import com.github.arhi23.photosocnet.core.entities.RemoteKeyEnt
import com.github.arhi23.photosocnet.core.entities.UserItemEnt

interface AppDb {
  fun userDao(): UserItemDao
  fun commentDao(): CommentDao
  fun likeDao(): LikeDao
  fun emoteDao(): EmoteDao
  fun mediaItemDao(): MediaItemDao
  fun remoteKeyDao(): RemoteKeyDao
}


@Database(
  entities = [UserItemEnt::class, CommentInfoEnt::class, LikeInfoEnt::class, EmoteInfoEnt::class, MediaInfoEnt::class, RemoteKeyEnt::class],
  version = 1
)
abstract class AppDbRoom : RoomDatabase(), AppDb