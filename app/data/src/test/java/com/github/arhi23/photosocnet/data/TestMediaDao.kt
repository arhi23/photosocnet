package com.github.arhi23.photosocnet.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.arhi23.photosocnet.data.daos.MediaItemDao
import com.github.arhi23.photosocnet.data.daos.UserItemDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
@UninstallModules(RoomDbModule::class)
@OptIn(ExperimentalCoroutinesApi::class)
class TestMediaDao {
  @Inject
  lateinit var appDb: AppDbRoom
  lateinit var mediaItemDao: MediaItemDao
  lateinit var userItemDao: UserItemDao

  @get:Rule(order = 0)
  val hiltRule: HiltAndroidRule by lazy { HiltAndroidRule(this) }

  @get:Rule(order = 1)
  val instantTaskExecutorRule = InstantTaskExecutorRule()

  @Before
  fun setup() {
    hiltRule.inject()
    mediaItemDao = appDb.mediaItemDao()
    userItemDao = appDb.userDao()
  }

  @After
  fun tearDown() {
    appDb.close()
  }

  @Test
  fun testAllInserted() = runTest {
    userItemDao.insertAll(userItems)
    mediaItemDao.insertAll(mediaItems)
    val items = mediaItemDao.getAll()
    assert( items.size == mediaItems.size )
  }

  @Test
  fun testMediaUsersAndCreatorSame() = runTest {
    userItemDao.insertAll(userItems)
    mediaItemDao.insertAll(mediaItems)
    val items = mediaItemDao.getAll()
    items.forEach {
      assert( it.userItem.serverId == it.mediaInfo.creatorId )
    }
  }

  @Test
  fun testUpdateMedia() = runTest {
    val newCommentCount = 0
    userItemDao.insertAll(userItems)
    mediaItemDao.insertAll(mediaItems)
    var item = mediaItemDao.findById(mediaItem1.contentId)
    assert( item.mediaInfo.commentCount == mediaItem1.commentCount )
    mediaItemDao.insert(mediaItem1.copy(commentCount = newCommentCount))
    val items = mediaItemDao.getAll()
    assert( items.size == mediaItems.size )
    item = mediaItemDao.findById(mediaItem1.contentId)
    assert( item.mediaInfo.commentCount == newCommentCount )
  }

  @Test
  fun testDeleteItem() = runTest {
    val itemsToDelete = listOf(mediaItem1)
    userItemDao.insertAll(userItems)
    mediaItemDao.insertAll(mediaItems)
    itemsToDelete.forEach { mediaItemDao.delete(it) }
    val items = mediaItemDao.getAll()

    assert( items.size == mediaItems.size-itemsToDelete.size )
  }

  @Test
  fun testMediaDeletedWithUserCascade() = runBlocking {
    userItemDao.insertAll(userItems)
    mediaItemDao.insertAll(mediaItems)
    userItemDao.deleteByUserId(mediaItem1.creatorId)
    val item = mediaItemDao.findById(mediaItem1.contentId)
    assert( item == null )
  }

}
