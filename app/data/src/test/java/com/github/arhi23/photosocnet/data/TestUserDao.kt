package com.github.arhi23.photosocnet.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.arhi23.photosocnet.data.daos.UserItemDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class TestUserDao {
  @Inject
  lateinit var appDb: AppDbRoom
  lateinit var userItemDao: UserItemDao

  @get:Rule(order = 0)
  val hiltRule: HiltAndroidRule by lazy { HiltAndroidRule(this) }

  @get:Rule(order = 1)
  val instantTaskExecutorRule = InstantTaskExecutorRule()

  @Before
  fun setup() {
    hiltRule.inject()
    userItemDao = appDb.userDao()
  }

  @After
  fun tearDown() {
    appDb.close()
  }

  @Test
  fun testAllInserted() = runTest {
    userItemDao.insertAll(userItems)
    val items = userItemDao.getAll()
    assert( items.size == userItems.size )
  }

  @Test
  fun testUpdate() = runTest {
    val testName = "New Name"
    userItemDao.insertAll(userItems)
    var item = userItemDao.findById(userItem1.uid)
    assert( item.name == userItem1.name )

    userItemDao.insert(userItem1.copy(name = testName))
    val items = userItemDao.getAll()
    assert( items.size == userItems.size )
    item = userItemDao.findById(userItem1.uid)
    assert( item.name == testName )
  }

  @Test
  fun testDeleteItem() = runTest {
    val itemsToDelete = listOf(userItem1)
    userItemDao.insertAll(userItems)
    itemsToDelete.forEach { userItemDao.delete(it) }


    val items = userItemDao.getAll()
    assert( items.size == userItems.size - itemsToDelete.size )
  }
}
