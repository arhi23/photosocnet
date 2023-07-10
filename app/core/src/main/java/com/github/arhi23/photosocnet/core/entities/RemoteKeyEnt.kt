package com.github.arhi23.photosocnet.core.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEnt(
  @PrimaryKey @ColumnInfo(name = "request") val request: String,
  @ColumnInfo(name = "last_key") val lastKey: String?
)