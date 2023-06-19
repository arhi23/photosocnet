package com.github.arhi23.photosocnet

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavBackStackEntry
import com.github.arhi23.photosocnet.destinations.ItemInfoScreenDestination
import com.ramcosta.composedestinations.spec.DestinationStyle

@ExperimentalAnimationApi
object ProfileTransitions : DestinationStyle.Animated {

  override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition? {
    return when (targetState.appDestination()) {
      ItemInfoScreenDestination ->
      slideInHorizontally(
        initialOffsetX = { 1000 },
        animationSpec = tween(300)
      )
    else -> null
  }
  }

  override fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition(): EnterTransition? {

    return when (initialState.appDestination()) {
      ItemInfoScreenDestination ->
        return slideInHorizontally(
          initialOffsetX = { -1000 },
          animationSpec = tween(300)
        )
      else -> null
    }
  }
}