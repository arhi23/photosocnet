package com.github.arhi23.photosocnet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.arhi23.photosocnet.core.entities.LikeInfoEnt

@Dao
interface LikeDao {

  @Query("SELECT * FROM likes")
  fun getAll(): List<LikeInfoEnt>

  @Query("SELECT * FROM likes WHERE content_id IN (:contentIds)")
  fun loadAllByIds(vararg contentIds: String): List<LikeInfoEnt>

  @Query(
    "SELECT * FROM likes WHERE id LIKE :id LIMIT 1"
  )
  fun findById(id: String): LikeInfoEnt

  @Query(
    "SELECT * FROM likes WHERE by_owner LIKE 1"
  )
  fun findAllOwner(): List<LikeInfoEnt>

  @Insert
  fun insertAll(likes: List<LikeInfoEnt>)

  @Delete
  fun delete(like: LikeInfoEnt)
}