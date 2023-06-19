package com.github.arhi23.photosocnet

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.github.arhi23.photosocnet.destinations.Destination
import com.github.arhi23.photosocnet.destinations.OwnerProfileScreenDestination
import com.github.arhi23.photosocnet.destinations.PhotoListScreenDestination
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

@Composable
fun BottomBar(
  navController: NavController
) {
  val currentDestination: Destination = navController.appCurrentDestinationAsState().value
    ?: NavGraphs.root.startAppDestination
  NavigationBar() {
    BottomBarDestination.values().forEach { destination ->
      NavigationBarItem(
        selected = currentDestination == destination.direction,
        onClick = {
          navController.navigateTo(destination.direction) {
            launchSingleTop = true
          }
        },
        icon = { Icon(destination.icon, contentDescription = stringResource(destination.label)) },
        label = { Text(stringResource(destination.label)) }
      )
    }
  }
}


enum class BottomBarDestination(
  val direction: DirectionDestinationSpec,
  val icon: ImageVector,
  @StringRes val label: Int
) {
  Feed(PhotoListScreenDestination, Icons.Default.Feed, R.string.feed),
  Profile(OwnerProfileScreenDestination, Icons.Default.PermIdentity, R.string.profile),
}