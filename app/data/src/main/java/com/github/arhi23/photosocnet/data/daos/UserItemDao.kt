package com.github.arhi23.photosocnet.data.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.arhi23.photosocnet.core.entities.UserItemEnt

@Dao
interface UserItemDao {
  @Query("SELECT * FROM users")
  fun getAll(): List<UserItemEnt>

  @Query("SELECT * FROM users")
  fun pagingSourceTimeline(): PagingSource<Int, UserItemEnt>

  @Query("SELECT * FROM users WHERE uid IN (:userIds)")
  fun loadAllByIds(userIds: IntArray): List<UserItemEnt>

  @Query(
    "SELECT * FROM users WHERE name LIKE :name LIMIT 1"
  )
  fun findByName(name: String): UserItemEnt

  @Query(
    "SELECT * FROM users WHERE uid LIKE :id LIMIT 1"
  )
  fun findById(id: String): UserItemEnt

  @Query(
    "SELECT * FROM users WHERE is_owner = 1 LIMIT 1"
  )
  fun getOwner(): UserItemEnt

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(users: List<UserItemEnt>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(user: UserItemEnt)

  @Query("DELETE FROM users WHERE uid = :userId")
  suspend fun deleteByUserId(userId: String)

  @Delete
  fun delete(user: UserItemEnt)

  @Query("SELECT EXISTS(SELECT * FROM users WHERE uid = :userId)")
  fun isExists(userId: String): Boolean

  @Query("DELETE FROM users")
  suspend fun deleteAll()
}