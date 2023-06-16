package com.github.arhi23.photosocnet.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.arhi23.photosocnet.data.entities.EmoteInfoEnt
import com.github.arhi23.photosocnet.data.entities.EmoteItem

@Dao
interface EmoteDao {
  @Query("SELECT * FROM emotes")
  fun getAll(): List<EmoteItem>

  @Query("SELECT * FROM emotes WHERE content_id IN (:contentIds)")
  fun loadAllByIds(vararg contentIds: String): List<EmoteItem>

  @Query(
    "SELECT * FROM emotes WHERE id LIKE :id LIMIT 1"
  )
  fun findById(id: String): EmoteItem

  @Query(
    "SELECT * FROM emotes WHERE by_owner LIKE 1"
  )
  fun findAllOwner(): List<EmoteItem>

  @Insert
  fun insertAll(emotes: List<EmoteInfoEnt>)

  @Delete
  fun delete(emote: EmoteInfoEnt)
}