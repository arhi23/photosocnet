package com.github.arhi23.photosocnet.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "emotes", foreignKeys = [
    ForeignKey(
      entity = MediaInfoEnt::class,
      parentColumns = ["content_id"],
      childColumns = ["content_id"],
      onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
      entity = UserItemEnt::class,
      parentColumns = ["uid"],
      childColumns = ["creator_id"],
      onDelete = ForeignKey.CASCADE
    )]
)
class EmoteInfoEnt(
  @PrimaryKey @ColumnInfo(name = "id") val id: String,
  @ColumnInfo(name = "content") val content: String,
  @ColumnInfo(name = "can_edit") val canEdit: Boolean,
  @ColumnInfo(name = "count") val count: Int,
  @ColumnInfo(name = "by_owner") val byOwner: Boolean,
  @ColumnInfo(name = "content_id") val contentId: String,
  @ColumnInfo(name = "creator_id") val creatorId: String,
)

data class EmoteItem(
  @Embedded val emoteInfo: EmoteInfoEnt,
  @Relation(
    parentColumn = "creator_id",
    entityColumn = "server_id"
  ) val userItem: UserItemEnt
)
