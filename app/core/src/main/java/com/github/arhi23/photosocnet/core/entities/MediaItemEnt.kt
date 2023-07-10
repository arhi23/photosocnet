package com.github.arhi23.photosocnet.core.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "medias", foreignKeys = [
  ForeignKey(
    entity = UserItemEnt::class,
    parentColumns = ["uid"],
    childColumns = ["creator_id"],
    onDelete = ForeignKey.CASCADE
  )])
data class MediaInfoEnt(
  @PrimaryKey @ColumnInfo(name = "content_id") val contentId: String,
  @ColumnInfo(name = "media_url") val mediaUrl: String,
  @ColumnInfo(name = "thumb_url") val thumbUrl: String,
  @ColumnInfo(name = "by_owner") val byOwner: Boolean,
  @ColumnInfo(name = "type") val type: String,
  @ColumnInfo(name = "created_at") val createdAt: Long,
  @ColumnInfo(name = "comment_count") val commentCount: Int,
  @ColumnInfo(name = "like_count") val likeCount: Int,
  @ColumnInfo(name = "creator_id") val creatorId: String,
)


data class MediaItem(
  @Embedded val mediaInfo: MediaInfoEnt,
  @Relation(
    parentColumn = "creator_id",
    entityColumn = "server_id"
  )
  val userItem: UserItemEnt,
//  @Relation(
//    parentColumn = "content_id",
//    entityColumn = "content_id"
//  ) val likes: List<LikeItem>,
//  @Relation(
//    entity = CommentInfoEnt::class,
//    parentColumn = "content_id",
//    entityColumn = "content_id"
//  )
//  val comments: List<CommentItem>,
//  @Relation(
//    parentColumn = "content_id",
//    entityColumn = "content_id"
//  )
//  val emotes: List<EmoteItem>
)