package com.github.arhi23.photosocnet.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "comments", foreignKeys = [
  ForeignKey(entity = MediaInfoEnt::class,
      parentColumns = ["content_id"],
    childColumns = ["content_id"],
    onDelete = ForeignKey.CASCADE),
  ForeignKey(entity = CommentInfoEnt::class,
    parentColumns = ["id"],
    childColumns = ["content_id"],
    onDelete = ForeignKey.CASCADE),
  ForeignKey(
    entity = UserItemEnt::class,
    parentColumns = ["uid"],
    childColumns = ["creator_id"],
    onDelete = ForeignKey.CASCADE
  )
])
data class CommentInfoEnt(
  @PrimaryKey @ColumnInfo(name = "id") val id: String,
  @ColumnInfo(name = "content") val content: String,
  @ColumnInfo(name = "can_edit") val canEdit: Boolean,
  @ColumnInfo(name = "by_owner") val byOwner: Boolean,
  @ColumnInfo(name = "comment_count") val commentCount: Int,
  @ColumnInfo(name = "like_count") val likeCount: Int,
  @ColumnInfo(name = "content_id") val contentId: String,
  @ColumnInfo(name = "created_at") val createdAt: Long,
  @ColumnInfo(name = "creator_id") val creatorId: String
)

data class CommentItem(
  @Embedded val commentInfoEnt: CommentInfoEnt,
  @Relation(
    parentColumn = "creator_id",
    entityColumn = "server_id"
  ) val userItem: UserItemEnt,
//  val likes: List<LikeItem>,
//  val comments: List<CommentItem>,
//  val emotes: List<EmoteItem>
)