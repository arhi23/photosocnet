package com.github.arhi23.photosocnet.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "followers", primaryKeys = ["follower", "followed"])
data class FollowRelationEnt(
  @ColumnInfo(name = "follower_id") val followerId: String,
  @ColumnInfo(name = "followed_id") val followedId: String
  )
