package com.github.arhi23.photosocnet.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(tableName = "followers", primaryKeys = ["followed_id", "follower_id"])
data class FollowerEnt(
  @ColumnInfo(name = "followed_id") val followedId: String,
  @ColumnInfo(name = "follower_id") val followerId: String
)

data class Followers(
  @Embedded val followed: UserItemEnt,
  @Relation(
    parentColumn = "uid",
    entityColumn = "uid",
    associateBy = Junction(FollowRelationEnt::class)
  )
  val followers: List<UserItemEnt>
)
