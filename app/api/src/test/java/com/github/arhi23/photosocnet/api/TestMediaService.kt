package com.github.arhi23.photosocnet.api

import com.github.arhi23.photosocnet.api.network.services.MediaRetrofitService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
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
class TestMediaService {
  @get:Rule(order = 0)
  val hiltRule: HiltAndroidRule = HiltAndroidRule(this)

  @Inject
  lateinit var mediaRetrofitService: MediaRetrofitService


  @Before
  fun setup() {
    hiltRule.inject()
  }

  @After
  fun tearDown() {
    MockServer.server.shutdown()
  }

  @Test
  fun testAppVersions() {
    runBlocking {
      MockServer.server.enqueueResponse("generatedq3.json", 200)
      val media = mediaRetrofitService.getMedia("0", "0",25)
      assert(media.isSuccessful)
      assert(media.body()?.items?.size!! == 5)
    }
  }
}