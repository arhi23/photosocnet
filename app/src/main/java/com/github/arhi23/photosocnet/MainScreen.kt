package com.github.arhi23.photosocnet

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.arhi23.photosocnet.composeui.PsnAppBar
import com.github.arhi23.photosocnet.destinations.Destination
import com.github.arhi23.photosocnet.destinations.ItemInfoScreenDestination
import com.github.arhi23.photosocnet.destinations.OwnerProfileScreenDestination
import com.github.arhi23.photosocnet.destinations.PhotoListScreenDestination
import com.github.arhi23.photosocnet.destinations.UserProfileScreenDestination
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
  ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
  ExperimentalMaterialNavigationApi::class
)
@Composable
fun MainScreen() {

  val snackbarHostState = remember { SnackbarHostState() }
  val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
  val engine = rememberAnimatedNavHostEngine()
  val navController = engine.rememberNavController()
  val navigateUp = remember { { navController.navigateUp() } }
  val destination = navController.appCurrentDestinationAsState().value
    ?: NavGraphs.root.startRoute.startAppDestination
  val config = destination.config()

  Scaffold(
    contentWindowInsets = WindowInsets.statusBars,
    topBar = {
      if (config.needTopBar) {
        PsnAppBar(
          modifier = Modifier.background(Color.Transparent),
          title = config.title,
          needBack = config.needBack,
          scrollBehavior = scrollBehavior,
          onNavigateUp = { navigateUp.invoke() }
        )
      }
    },
    bottomBar = {
      if (config.needBottomBar) {
        BottomBar(navController)
      }
    },
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState) { data ->
        Snackbar(snackbarData = data)
      }
    }, content = {
      DestinationsNavHost(
        engine = engine,
        navController = navController,
        navGraph = NavGraphs.root,
        modifier = Modifier.padding(it),
        startRoute = NavGraphs.root.startRoute.startAppDestination
      )
    })
}

@Composable
fun Destination.config(): DestinationUiConfig {
  return when (this) {
    PhotoListScreenDestination -> DestinationUiConfig(false, "Feed", true, true)
    OwnerProfileScreenDestination -> DestinationUiConfig(false, "Profile", true, true)
    UserProfileScreenDestination -> DestinationUiConfig(true, "UserProfile", true, false)
    ItemInfoScreenDestination -> DestinationUiConfig(true, "UserProfile", true, false)
    else -> DestinationUiConfig(false, "feed", true, true)
  }
}

data class DestinationUiConfig(
  val needBack: Boolean,
  val title: String,
  val needTopBar: Boolean,
  val needBottomBar: Boolean,
)