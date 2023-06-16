package com.github.arhi23.photosocnet.data.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.arhi23.photosocnet.data.entities.MediaInfoEnt
import com.github.arhi23.photosocnet.data.entities.MediaItem

@Dao
interface MediaItemDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(medias: List<MediaInfoEnt>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(medias: MediaInfoEnt)

  @Query("SELECT * FROM medias ORDER BY created_at")
  fun pagingSourceTimeline(): PagingSource<Int, MediaItem>

  @Query("SELECT * FROM medias")
  fun getAll(): List<MediaItem>

  @Query("SELECT * FROM medias ORDER BY created_at")
  fun getAllTimeline(): List<MediaItem>

  @Query("SELECT * FROM medias WHERE content_id IN (:contentIds)")
  fun loadAllByIds(vararg contentIds: String): List<MediaItem>

  @Query(
    "SELECT * FROM medias WHERE content_id LIKE :contentId LIMIT 1"
  )
  fun findById(contentId: String): MediaItem

  @Query(
    "SELECT * FROM medias WHERE by_owner LIKE 1"
  )
  fun findAllOwner(): List<MediaItem>

  @Delete
  suspend fun delete(media: MediaInfoEnt)

  @Query("DELETE FROM medias")
  suspend fun deleteAll()
}