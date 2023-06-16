package com.github.arhi23.photosocnet.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserItemEnt(
  @PrimaryKey @ColumnInfo(name = "uid") val uid: String,
  @ColumnInfo(name = "name") val name: String,
  @ColumnInfo(name = "is_owner") val isOwner: Boolean,
  @ColumnInfo(name = "description") val description: String,
  @ColumnInfo(name = "avatar_url") val avatarUrl: String,
  @ColumnInfo(name = "server_id") val serverId: String
)