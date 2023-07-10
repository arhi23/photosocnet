package com.github.arhi23.photosocnet.core.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(tableName = "followings", primaryKeys = ["follower_id", "followed_id"])
data class FollowingEnt(
  @ColumnInfo(name = "follower_id") val followerId: String,
  @ColumnInfo(name = "followed_id") val followedId: String
)

data class Followings(
  @Embedded val follower: UserItemEnt,
  @Relation(
    parentColumn = "uid",
    entityColumn = "uid",
    associateBy = Junction(FollowRelationEnt::class)
  )
  val followed: List<UserItemEnt>
)