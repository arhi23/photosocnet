package com.github.arhi23.photosocnet.composeui

import androidx.compose.animation.Crossfade
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.arhi23.photosocnet.resources.R.string

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PsnAppBar(
  title: String = "",
  scrollBehavior: TopAppBarScrollBehavior,
  modifier: Modifier = Modifier,
  needBack: Boolean = false,
  onNavigateUp: () -> Unit = {}
) {
  TopAppBar(
    navigationIcon = {
      if (needBack) {
        IconButton(onClick = onNavigateUp) {
          Icon(
            Icons.Default.ArrowBack,
            contentDescription = stringResource(id = string.back),
          )
        }
      }
    },
    modifier = modifier,
    scrollBehavior = scrollBehavior,
    title = { Text(text = title) }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PsnSearchAppBar(
  title: String,
  onNavigateUp: () -> Unit,
  showSearch: () -> Unit,
  scrollBehavior: TopAppBarScrollBehavior,
  modifier: Modifier = Modifier,
) {
  TopAppBar(
    navigationIcon = {
      IconButton(onClick = onNavigateUp) {
        Icon(
          Icons.Default.ArrowBack,
          contentDescription = stringResource(id = string.back),
        )
      }
    },
    modifier = modifier,
    scrollBehavior = scrollBehavior,
    title = { Text(text = title) },

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PsnRefreshAppBar(
  title: String,
  refreshing: Boolean,
  onNavigateUp: () -> Unit,
  onRefreshActionClick: () -> Unit,
  scrollBehavior: TopAppBarScrollBehavior,
  modifier: Modifier = Modifier,
) {
  TopAppBar(
    navigationIcon = {
      IconButton(onClick = onNavigateUp) {
        Icon(
          Icons.Default.ArrowBack,
          contentDescription = stringResource(id = string.back),
        )
      }
    },
    modifier = modifier,
    scrollBehavior = scrollBehavior,
    title = { Text(text = title) },
    actions = {
      Crossfade(
        targetState = refreshing,
        modifier = Modifier.align(Alignment.CenterVertically),
      ) { isRefreshing ->
        if (!isRefreshing) {
          RefreshButton(onClick = onRefreshActionClick)
        }
      }
    },
  )
}