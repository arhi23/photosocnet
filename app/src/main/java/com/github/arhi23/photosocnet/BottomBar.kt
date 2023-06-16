package com.github.arhi23.photosocnet

import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
  val currentDestination: Destination? = navController.appCurrentDestinationAsState().value
    ?: NavGraphs.root.startAppDestination
  BottomNavigation(elevation = 10.dp) {
    BottomBarDestination.values().forEach { destination ->
      BottomNavigationItem(
        selected = currentDestination == destination.direction,
        onClick = {
          navController.navigateTo(destination.direction) {
            launchSingleTop = true
          }
        },
        icon = { Icon(destination.icon, contentDescription = stringResource(destination.label)) },
        label = { Text(stringResource(destination.label)) },
        selectedContentColor = Color.White
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