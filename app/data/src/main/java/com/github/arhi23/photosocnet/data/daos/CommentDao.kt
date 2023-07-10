package com.github.arhi23.photosocnet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.arhi23.photosocnet.core.entities.CommentInfoEnt
import com.github.arhi23.photosocnet.core.entities.CommentItem

@Dao
interface CommentDao {
  @Query("SELECT * FROM comments")
  fun getAll(): List<CommentItem>

  @Query("SELECT * FROM comments WHERE content_id IN (:contentIds)")
  fun loadAllByIds(vararg contentIds: String): List<CommentItem>

  @Query(
    "SELECT * FROM comments WHERE id LIKE :id LIMIT 1"
  )
  fun findById(id: String): CommentItem

  @Query(
    "SELECT * FROM comments WHERE by_owner LIKE 1"
  )
  fun findAllOwner(): List<CommentItem>

  @Insert
  fun insertAll(comments: List<CommentInfoEnt>)

  @Delete
  fun delete(comment: CommentInfoEnt)
}