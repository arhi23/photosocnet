package com.github.arhi23.photosocnet.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.arhi23.photosocnet.core.entities.RemoteKeyEnt

@Dao
interface RemoteKeyDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertOrReplace(remoteKey: RemoteKeyEnt)

  @Query("SELECT * FROM remote_keys WHERE request = :request")
  suspend fun getByRequest(request: String): RemoteKeyEnt?

  @Query("DELETE FROM remote_keys WHERE request = :request")
  suspend fun delete(request: String)
}