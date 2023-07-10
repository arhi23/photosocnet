package com.github.arhi23.photosocnet.ownerprofile

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
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.arhi23.photosocnet.resources.R.string
import com.github.arhi23.photosocnet.UiSideEffect
import com.github.arhi23.photosocnet.UiStatus.Empty
import com.github.arhi23.photosocnet.UiStatus.Loaded
import com.github.arhi23.photosocnet.UiStatus.Loading
import com.github.arhi23.photosocnet.UiStatus.NoInternet
import com.github.arhi23.photosocnet.UiStatus.ServerProblem
import com.github.arhi23.photosocnet.composeui.Avatar
import com.github.arhi23.photosocnet.composeui.theme.PsnTheme
import com.github.arhi23.photosocnet.core.entities.UserItemEnt
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState

@Preview
@Composable
fun PreviewOwnerProfile() {
  PsnTheme {
    ScreenContent(
      UiSideEffect.NoEffect,
      { _, _ -> },
      OwnerProfileState(Empty)
    )
  }
}

@Destination
@SuppressLint("NotConstructor")
@Composable
fun OwnerProfileScreen(
  viewModel: OwnerProfileViewModel = hiltViewModel(),
  navigator: DestinationsNavigator
) {
  val screenState by viewModel.collectAsState()
  ScreenContent(
    UiSideEffect.NoEffect,
    viewModel::saveOwner,
    screenState
  )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
private fun ScreenContent(
  showSnackBar: UiSideEffect,
  saveInfo: (String, String) -> Unit,
  screenState: OwnerProfileState
) {
  val scope = rememberCoroutineScope()
  val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
  val scaffoldState = rememberScaffoldState()

  Scaffold(
    content = { innerPadding ->
      when (screenState.status) {
        Loading -> {
        }
        ServerProblem -> {
          Text(text = stringResource(id = string.server_problem), modifier = Modifier.fillMaxWidth())
        }
        NoInternet -> {
          Text(text = stringResource(id = string.no_internet), modifier = Modifier.fillMaxWidth())
        }
        Loaded -> {
          val userItem = screenState.userItem ?: return@Scaffold
          ProfileInfo(
            saveInfo,
            Modifier.padding(innerPadding),
            userItem
          )
        }
        else -> {}
      }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInfo(
  saveInfo: (String, String) -> Unit,
  modifier: Modifier,
  userItemEnt: UserItemEnt
) {
  val maxNameChar = 32
  val maxDescriptionChar = 164
  var name by rememberSaveable { mutableStateOf(userItemEnt.name) }
  var description by rememberSaveable { mutableStateOf(userItemEnt.description) }

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
    Spacer(modifier = Modifier.height(32.dp))
    TextField(modifier = Modifier
      .fillMaxWidth(),
      value = name,
      singleLine = true,
      onValueChange = {
        if (it.length <= maxNameChar) name = it
      },
      label = { Text(
        text = stringResource(id = string.name),
        maxLines = 1
      ) },
      supportingText = {
        Text(
          text = "${name.length} / $maxNameChar",
          modifier = Modifier.fillMaxWidth(),
          textAlign = TextAlign.End,
        )
      },
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextField(modifier = Modifier
      .fillMaxWidth(),
      maxLines = 3,
      value = description,
      onValueChange = {
        if (it.length <= maxDescriptionChar) description = it
      },
      label = { Text(
        text = stringResource(id = string.description),
        maxLines = 1 ) },
      supportingText = {
        Text(
          text = "${description.length} / $maxDescriptionChar",
          modifier = Modifier.fillMaxWidth(),
          textAlign = TextAlign.End,
        )
      },
    )
    Spacer(modifier = Modifier.height(32.dp))
    Button(onClick = { saveInfo(name, description) }) {
      Text(text = stringResource(id = string.save_all))
    }
  }
}