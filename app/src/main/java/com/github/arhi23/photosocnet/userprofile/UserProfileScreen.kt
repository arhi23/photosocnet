package com.github.arhi23.photosocnet.userprofile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.arhi23.photosocnet.UiSideEffect
import com.github.arhi23.photosocnet.UiStatus
import com.github.arhi23.photosocnet.resources.R.string
import com.github.arhi23.photosocnet.composeui.Avatar
import com.github.arhi23.photosocnet.composeui.theme.PsnTheme
import com.github.arhi23.photosocnet.data.entities.UserItemEnt
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

//@Preview(
//  name = "dark theme",
//  group = "themes",
//  uiMode = UI_MODE_NIGHT_YES
//)
@Preview
@Composable
fun PreviewOwnerProfile() {
  PsnTheme {
    ScreenContent(
      UiSideEffect.NoEffect,
      { },
      UserProfileState(UiStatus.Empty)
    )
  }
}

@Destination(
  navArgsDelegate = UserProfileNavParams::class
)
@SuppressLint("NotConstructor")
@Composable
fun UserProfileScreen(
  viewModel: UserProfileViewModel = hiltViewModel(),
  navParams: UserProfileNavParams,
  navigator: DestinationsNavigator
) {
  val state by viewModel.collectAsState()
  viewModel.collectSideEffect {

  }
  ScreenContent(
    UiSideEffect.NoEffect,
    viewModel::openFollowers,
    state
  )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
private fun ScreenContent(
  showSnackBar: UiSideEffect,
  openFollowers: (String) -> Unit,
  screenState: UserProfileState
) {

  val scope = rememberCoroutineScope()
  val snackbarHostState = remember { SnackbarHostState() }

  Scaffold(
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState) { data ->
        Snackbar(snackbarData = data)
      }
    },
    content = { innerPadding ->
      when (screenState.status) {
        UiStatus.Loading -> {
        }
        UiStatus.ServerProblem -> {
          Text(text = stringResource(id = string.server_problem), modifier = Modifier.fillMaxWidth())
        }
        UiStatus.NoInternet -> {
          Text(text = stringResource(id = string.no_internet), modifier = Modifier.fillMaxWidth())
        }
        UiStatus.Loaded -> {
          val userItem = screenState.userItem ?: return@Scaffold
          ProfileInfo(openFollowers, Modifier.padding(innerPadding), userItem)
        }
        else -> {}
      }
    })
}

@Composable
fun ProfileInfo(
  openFollowers: (String) -> Unit,
  modifier: Modifier,
  userItemEnt: UserItemEnt
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .verticalScroll(rememberScrollState())
      .padding(16.dp),
    horizontalAlignment = CenterHorizontally
  ) {
    Avatar(
      avatarUrl = userItemEnt.avatarUrl,
      modifier = Modifier
        .align(CenterHorizontally)
        .size(150.dp)
        .padding(4.dp)
    )
    Spacer(modifier = Modifier.height(64.dp))
    Text(
      modifier = Modifier
        .padding(start = 32.dp, end = 32.dp)
        .fillMaxWidth(),
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,
      text = userItemEnt.name,
      style = MaterialTheme.typography.headlineSmall
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
      modifier = Modifier
        .padding(start = 32.dp, end = 32.dp)
        .fillMaxWidth(),
      maxLines = 6,
      overflow = TextOverflow.Ellipsis,
      text = userItemEnt.description,
      style = MaterialTheme.typography.bodyLarge
    )
  }
}