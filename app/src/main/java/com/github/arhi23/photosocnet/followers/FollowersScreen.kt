package com.github.arhi23.photosocnet.followers

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DismissValue.Default
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState.Loading
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.arhi23.photosocnet.R.drawable
import com.github.arhi23.photosocnet.UiSideEffect
import com.github.arhi23.photosocnet.composeui.PsnAppBar
import com.github.arhi23.photosocnet.core.entities.UserItemEnt
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Preview
@Composable
fun PreviewFollowers() {
//  PsnTheme {
//    FollowersSearchField()
//    ScreenContent(
//      {},
//      {},
//      {},
//      lazyPagingItems = flowOf(PagingData.from(listOf("sada", "dasdsa")))
//        .collectAsLazyPagingItems(),
//      FollowersSideEffect.Nothing
//    )
//  }
}


@Destination(
  navArgsDelegate = FollowersNavParams::class
)
@SuppressLint("NotConstructor")
@Composable
fun FollowersList(
  navigator: DestinationsNavigator,
  navParams: FollowersNavParams,
  viewModel: FollowersViewModel = hiltViewModel()
) {
  val state = viewModel.collectAsState().value
  var showSnackBar by remember { mutableStateOf<UiSideEffect>(UiSideEffect.NoEffect) }
  viewModel.collectSideEffect {
    when (it) {
      is UiSideEffect.ShowSnackbar -> showSnackBar = it
      else -> {}
    }
  }

  FollowersSearchField()

  ScreenContent(
    { navigator?.navigateUp() },
    { viewModel.removeFollower(it) },
    { viewModel.openUser(it) },
    viewModel.lazyPagingItems.collectAsLazyPagingItems(),
    showSnackBar
  )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
private fun ScreenContent(
  onNavigateUp: () -> Unit,
  onRemoveClick: (id: String) -> Unit,
  onUserClick: (id: String) -> Unit,
  lazyPagingItems: LazyPagingItems<UserItemEnt>,
  showSnackBar: UiSideEffect
) {

  val snackbarHostState = remember { SnackbarHostState() }
  val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
  val scaffoldState = rememberScaffoldState()
  val dismissSnackbarState = rememberDismissState { value ->
    when {
      value != Default -> {
        snackbarHostState.currentSnackbarData?.dismiss()
        true
      }

      else -> false
    }
  }
  Scaffold(
    contentWindowInsets = WindowInsets.statusBars,
    topBar = {
      PsnAppBar(
        modifier = Modifier.background(Color.Transparent),
        title = "Followers",
        scrollBehavior = scrollBehavior,
        onNavigateUp = onNavigateUp
      )
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        onClick = {

        }
      ) { Text("Show snackbar") }
    },
    content = { innerPadding ->
      FollowersList(innerPadding, lazyPagingItems, onRemoveClick, onUserClick)
    })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FollowersList(
  innerPadding: PaddingValues,
  lazyPagingItems: LazyPagingItems<UserItemEnt>,
  onRemoveClick: (id: String) -> Unit,
  onUserClick: (id: String) -> Unit,
) {
  val refreshing = lazyPagingItems.loadState.refresh == Loading
  val refreshState = rememberPullRefreshState(
    refreshing = refreshing,
    onRefresh = lazyPagingItems::refresh,
  )

  Box(modifier = Modifier.pullRefresh(state = refreshState)) {
    LazyColumn(
      contentPadding = innerPadding,
      modifier = Modifier
        .background(Color.Gray)
    ) {
//        FollowerRow(
//          photo = item,
//          onRemoveClick = onRemoveClick,
//          onUserClick = onUserClick,
//          onFollowClick = onUserClick,
//          modifier = Modifier
//            .fillParentMaxWidth()
//            .clickable { onUserClick(item.first) },
//        )
//
    }

    PullRefreshIndicator(
      refreshing = refreshing,
      state = refreshState,
      modifier = Modifier
        .align(Alignment.TopCenter)
        .padding(innerPadding),
      scale = true,
    )
  }
}

@Composable
fun FollowerRow(
  photo: Pair<String, Int>,
  modifier: Modifier = Modifier,
  onRemoveClick: (id: String) -> Unit,
  onFollowClick: (id: String) -> Unit,
  onUserClick: (id: String) -> Unit,
) {
  Row(
    modifier = modifier
      .wrapContentHeight()
      .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
      .clip(RoundedCornerShape(8.dp))
      .background(Color.Yellow)
      .clickable { onUserClick(photo.first) }
  ) {
    Image(
      painter = painterResource(id = drawable.app_t),
      contentDescription = "",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .padding(8.dp)
        .size(32.dp)
        .clip(CircleShape)
    )
    Text(
      modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .weight(0.6f)
        .align(Alignment.CenterVertically)
        .padding(start = 4.dp, end = 8.dp),
      text = photo.first,
      style = MaterialTheme.typography.bodySmall,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis,
      textAlign = TextAlign.Start
    )
    TextButton(
      modifier = Modifier
        .wrapContentHeight()
        .wrapContentWidth(),
      onClick = { onRemoveClick(photo.first) }
    ) {
      Text(
        text = "Remove",
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Black
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FollowersSearchField() {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .height(56.dp),
    shadowElevation = AppBarDefaults.TopAppBarElevation,
    color = MaterialTheme.colorScheme.primary
  ) {
    TextField(modifier = Modifier
      .fillMaxWidth(),
      value = "",
      onValueChange = {
      },
      placeholder = {
        Text(
          modifier = Modifier
            .alpha(ContentAlpha.medium),
          text = "Search",
          color = Color.White
        )
      },
      singleLine = true,
      leadingIcon = {
        IconButton(
          modifier = Modifier
            .alpha(ContentAlpha.medium),
          onClick = {}
        ) {
          Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            tint = Color.White
          )
        }
      },
      trailingIcon = {
        IconButton(
          onClick = {
          }
        ) {
          Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close Icon",
            tint = Color.White
          )
        }
      },
      keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Search
      ),
      keyboardActions = KeyboardActions(
        onSearch = {
        }
      ),
      colors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
        cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
      ))
  }
}

