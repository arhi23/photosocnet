package com.github.arhi23.photosocnet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.github.arhi23.photosocnet.composeui.theme.PsnTheme
import com.ramcosta.composedestinations.annotation.Destination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PsnTheme {
        MainScreen()
      }

    }
  }
}