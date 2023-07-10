package com.github.arhi23.photosocnet.data.daos

import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.arhi23.photosocnet.core.entities.MediaItem
import com.github.arhi23.photosocnet.core.entities.UserItemEnt

interface FollowDao {

  @Query("SELECT * FROM followings")
  fun getAll(): List<UserItemEnt>


  @Query("SELECT * FROM followings")
  fun pagingSourceFollowing(): PagingSource<Int, MediaItem>

  @Query("SELECT * FROM followings WHERE follower_id = :followingId")
  fun loadAllById(followingId: IntArray): List<UserItemEnt>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(users: List<UserItemEnt>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(user: UserItemEnt)

  @Query("DELETE FROM followings WHERE follower_id = :followingId")
  suspend fun deleteByUserId(followingId: String)

  @Delete
  fun delete(userId: String)

  @Query("SELECT EXISTS(SELECT * FROM followings WHERE follower_id = :followingId AND followed_id = :followedId )")
  fun isFollowing(followingId: String, followedId: String): Boolean

  @Query("DELETE FROM followings")
  suspend fun deleteAll()
}