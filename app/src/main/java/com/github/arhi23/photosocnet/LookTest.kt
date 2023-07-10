package com.github.arhi23.photosocnet

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.LookaheadLayout
import androidx.compose.ui.layout.LookaheadLayoutScope
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import kotlinx.coroutines.launch
import java.lang.Integer.max

@Preview
@Composable
fun Previewі() {
  ExpandableFabBasic()
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExpandableFabBasic(modifier: Modifier = Modifier) {
  var isExpanded by remember { mutableStateOf(false) }
  val screenMaxWidth = LocalConfiguration.current.screenWidthDp
  LookaheadLayout(
    modifier = modifier
      .fillMaxSize()
      .navigationBarsPadding()
      .padding(16.dp),
    content = {
      Text(
        text = "dasdsa",
        modifier = Modifier
          .size(
            size = if (isExpanded) 100.dp else 200.dp
          )
          .animateMovement(lookaheadScope = this)
          .animateTransformation(lookaheadScope = this)
          .clickable { isExpanded = !isExpanded },
      )
    },
    measurePolicy = MeasurePolicy { measurables, constraints ->
      val contentConstraints = constraints.copy(minWidth = 0, minHeight = 0)
      val placeables = measurables.map { it.measure(contentConstraints) }
      val maxWidth: Int = max(placeables.maxOf { it.width }, constraints.minWidth)
      val maxHeight = max(placeables.maxOf { it.height }, constraints.minHeight)
      // Position the children.
      layout(maxWidth, maxHeight) {
        placeables.forEach {
          it.place(0, 0)
        }
      }
    }
  )
}

@OptIn(ExperimentalComposeUiApi::class) fun Modifier.animateMovement(
  lookaheadScope: LookaheadLayoutScope,
  animationSpec: AnimationSpec<IntOffset> = spring()
) = composed {
  var placementOffset by remember { mutableStateOf(IntOffset.Zero) }
  var targetOffset: IntOffset? by remember { mutableStateOf(null) }

  var targetOffsetAnimation: Animatable<IntOffset, AnimationVector2D>? by remember {
    mutableStateOf(null)
  }

  LaunchedEffect(Unit) {
    snapshotFlow { targetOffset }.collect { target ->
      if (target != null && target != targetOffsetAnimation?.targetValue) {
        targetOffsetAnimation?.run {
          launch {
            animateTo(
              targetValue = target,
              animationSpec = animationSpec
            )
          }
        } ?: Animatable(
          initialValue = target,
          typeConverter = IntOffset.VectorConverter
        ).let { offsetAnimatable ->
          targetOffsetAnimation = offsetAnimatable
        }
      }
    }
  }

  with(lookaheadScope) {
    this@composed
      .onPlaced { lookaheadScopeCoordinates, layoutCoordinates ->
        targetOffset = lookaheadScopeCoordinates
          .localLookaheadPositionOf(sourceCoordinates = layoutCoordinates)
          .round()

        placementOffset = lookaheadScopeCoordinates
          .localPositionOf(
            sourceCoordinates = layoutCoordinates,
            relativeToSource = Offset.Zero
          )
          .round()
      }
      .intermediateLayout { measurable, constraints, _ ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
          val (x, y) = (
              targetOffsetAnimation?.value ?: targetOffset!!
              ) - placementOffset
          placeable.place(x, y)
        }
      }
  }
}

@OptIn(ExperimentalComposeUiApi::class) fun Modifier.animateTransformation(
  lookaheadScope: LookaheadLayoutScope,
  animationSpec: AnimationSpec<IntSize> = spring(),
) = composed {
  var targetSize: IntSize? by remember { mutableStateOf(null) }
  var targetSizeAnimation: Animatable<IntSize, AnimationVector2D>? by remember {
    mutableStateOf(null)
  }

//  LaunchedEffect(Unit) {
//    snapshotFlow { targetSize }.collect { target ->
//      if (target != null && target != targetSizeAnimation?.targetValue) {
//        targetSizeAnimation?.run {
//          launch {
//            animateTo(
//              targetValue = target,
//              animationSpec = animationSpec
//            )
//          }
//        } ?: Animatable(
//          initialValue = target,
//          typeConverter = IntSize.VectorConverter
//        ).let { sizeAnimatable ->
//          targetSizeAnimation = sizeAnimatable
//        }
//      }
//    }
//  }

  with(lookaheadScope) {
    this@composed.intermediateLayout { measurable, _, lookaheadSize ->
      targetSize = lookaheadSize

      val (width, height) = targetSizeAnimation?.value ?: lookaheadSize
      val animatedConstraints = Constraints.fixed(
        width = width.coerceAtLeast(0),
        height = height.coerceAtLeast(0)
      )

      val placeable = measurable.measure(animatedConstraints)
      layout(width = placeable.width, height = placeable.height) {
        placeable.place(x = 0, y = 0)
      }
    }
  }
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.transformation(lookaheadScope: LookaheadLayoutScope) = with(lookaheadScope) {
  intermediateLayout { measurable, _, lookaheadSize ->
    val (width, height) = lookaheadSize // Determine width and height by lookahead size
    val animatedConstraints = Constraints.fixed(
      width = width.coerceAtLeast(0), // Minimum set to 0
      height = height.coerceAtLeast(0)
    )

    val placeable = measurable.measure(animatedConstraints)
    layout(width = placeable.width, height = placeable.height) { // Layout to fit lookahead size
      placeable.place(x = 0, y = 0)
    }
  }
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.movement(lookaheadScope: LookaheadLayoutScope) = composed {
  var targetOffset: IntOffset? by remember { mutableStateOf(null) } // offset to place
  var placementOffset by remember { mutableStateOf(IntOffset.Zero) } // current offset

  with(lookaheadScope) {
    this@composed
      .onPlaced { lookaheadScopeCoordinates, layoutCoordinates ->
        // Returns the lookahead position of this modifier in the local coordinates of the LookaheadLayout
        targetOffset = lookaheadScopeCoordinates
          .localLookaheadPositionOf(sourceCoordinates = layoutCoordinates)
          .round() // Rounding offsets to the nearest IntOffset value

        // Returns the current position of this modifier in the local coordinates of the LookaheadLayout
        placementOffset = lookaheadScopeCoordinates
          .localPositionOf(
            sourceCoordinates = layoutCoordinates,
            relativeToSource = Offset.Zero
          )
          .round()
      }
      .intermediateLayout { measurable, constraints, _ ->
        val placeable = measurable.measure(constraints)
        layout(width = placeable.width, height = placeable.height) {
          // Place at moved offset
          val (x, y) = targetOffset!! - placementOffset
          placeable.place(x = x, y = y)
        }
      }
  }
}
