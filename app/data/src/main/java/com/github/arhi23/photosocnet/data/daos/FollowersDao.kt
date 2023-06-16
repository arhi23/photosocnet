package com.github.arhi23.photosocnet.data.daos

import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.github.arhi23.photosocnet.data.entities.Followers
import com.github.arhi23.photosocnet.data.entities.MediaItem
import com.github.arhi23.photosocnet.data.entities.UserItemEnt

interface FollowersDao {

  @Query("SELECT * FROM followers")
  fun pagingSourceFollowed(): PagingSource<Int, MediaItem>

  @Query("SELECT * FROM followers")
  fun getAll(): List<UserItemEnt>

  @Transaction
  @Query("SELECT * FROM followers")
  fun getFollowers(): List<Followers>



  @Query("SELECT * FROM followers")
  fun pagingSourceFollowers(): PagingSource<Int, Followers>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(userIds: List<Followers>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(userId: Followers)

  @Query("DELETE FROM followers WHERE follower_id = :followerId")
  suspend fun deleteByUserId(followerId: String)

  @Delete
  fun delete(userId: String)

  @Query("SELECT EXISTS(SELECT * FROM followers WHERE follower_id = :followerId AND followed_id = :followedId )")
  fun isFollowing(followerId: String, followedId: String): Boolean

  @Query("DELETE FROM followers")
  suspend fun deleteAll()
}