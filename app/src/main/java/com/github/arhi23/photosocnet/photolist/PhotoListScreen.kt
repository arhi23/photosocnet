package com.github.arhi23.photosocnet.photolist

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState.Loading
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.arhi23.photosocnet.ProfileTransitions
import com.github.arhi23.photosocnet.resources.R.string
import com.github.arhi23.photosocnet.UiSideEffect
import com.github.arhi23.photosocnet.composeui.MediaContent
import com.github.arhi23.photosocnet.composeui.PostTop
import com.github.arhi23.photosocnet.data.PagingNullKey
import com.github.arhi23.photosocnet.data.appendErrorOrNull
import com.github.arhi23.photosocnet.data.entities.MediaItem
import com.github.arhi23.photosocnet.data.prependErrorOrNull
import com.github.arhi23.photosocnet.data.refreshErrorOrNull
import com.github.arhi23.photosocnet.destinations.ItemInfoScreenDestination
import com.github.arhi23.photosocnet.destinations.UserProfileScreenDestination
import com.github.arhi23.photosocnet.iteminfo.MediaFullNavParams
import com.github.arhi23.photosocnet.userprofile.UserProfileNavParams
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Preview
@Composable
fun PreviewPhotoBar() {
//  PsnTheme {
//    PhotoSearchField(Sosl(UserSS("sa")))
//    ScreenContent(
//      {},
//      {},
//      lazyPagingItems = flowOf(
//        PagingData.from(
//          listOf(
//            MediaItem(
//              MediaInfoEnt(
//                contentId = "131415",
//                mediaUrl = "https://example.com/media5.jpg",
//                thumbUrl = "https://example.com/media5_thumb.jpg",
//                byOwner = false,
//                type = "image",
//                createdAt = 1642984800,
//                commentCount = 4,
//                likeCount = 5,
//                creatorId = "user456"
//              ), UserItemEnt(
//                uid = "user123",
//                name = "John Doe",
//                isOwner = false,
//                description = "I'm a software engineer",
//                avatarUrl = "https://example.com/avatar1.jpg",
//                serverId = "user123"
//              )
//            )
//          )
//        )
//      )
//        .collectAsLazyPagingItems(),
//      UiSideEffect.NoEffect
//    )
//  }

}

//todo snackbar generatedesc generatetags
@OptIn(ExperimentalAnimationApi::class)
@Destination(
  style = ProfileTransitions::class)
@RootNavGraph(start = true)
@SuppressLint("NotConstructor")
@Composable
fun AnimatedVisibilityScope.PhotoListScreen(
  navigator: DestinationsNavigator,
  viewModel: PhotolistViewModel = hiltViewModel()
) {
  val state = viewModel.collectAsState()
  var sideEffect by remember { mutableStateOf<UiSideEffect>(UiSideEffect.NoEffect) }

  val onPhotoClick =
    remember {
      { mediaId: String ->
        navigator.navigate(
          ItemInfoScreenDestination(
            MediaFullNavParams(mediaId = mediaId)
          )
        )
      }
    }
  val onUserClick =
    remember {
      { userId: String ->
        navigator.navigate(
          UserProfileScreenDestination(
            UserProfileNavParams(userId = userId)
          )
        )
      }
    }

  viewModel.collectSideEffect {
    sideEffect = it
  }

  ScreenContent(
    onPhotoClick,
    onUserClick,
    viewModel.lazyPagingItems.collectAsLazyPagingItems(),
    sideEffect
  )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ScreenContent(
  onPhotoClick: (String) -> Unit,
  onUserClick: (String) -> Unit,
  lazyPagingItems: LazyPagingItems<MediaItem>,
  showSnackBar: UiSideEffect
) {

  val snackbarHostState = remember { SnackbarHostState() }
  LaunchedEffect(showSnackBar) {
//    snackbarHostState.showSnackbar(showSnackBar.toString())
  }
  lazyPagingItems.loadState.prependErrorOrNull()?.let { message ->
    LaunchedEffect(message) {
      message.message?.let {
        snackbarHostState.showSnackbar(it)
      }
    }
  }
  lazyPagingItems.loadState.appendErrorOrNull()?.let { message ->
    LaunchedEffect(message) {
      message.message?.let {
        snackbarHostState.showSnackbar(it)
      }
    }
  }
  lazyPagingItems.loadState.refreshErrorOrNull()?.let { message ->
    LaunchedEffect(message) {
      message.message?.let {
        snackbarHostState.showSnackbar(it)
      }
    }
  }

  Scaffold(
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState) { data ->
        Snackbar(snackbarData = data)
      }
    },
    content = { innerPadding ->
      PhotoList(innerPadding, lazyPagingItems, onPhotoClick, onUserClick)
    })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotoList(
  innerPadding: PaddingValues,
  lazyPagingItems: LazyPagingItems<MediaItem>,
  onPhotoClick: (id: String) -> Unit,
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
      modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
      items(
        count = lazyPagingItems.itemCount,
        key = { index ->
          val item = lazyPagingItems.peek(index)
          if (item != null) {
            item.mediaInfo.contentId
          } else {
            PagingNullKey(index)
          }
        }
      ) { index ->
        lazyPagingItems[index]?.let {
          PhotoRow(
            photo = it,
            onPhotoClick = onPhotoClick,
            onUserClick = onUserClick,
            modifier = Modifier
              .fillParentMaxWidth()
              .clickable { onPhotoClick(it.mediaInfo.contentId) },
          )
        }
      }
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
fun PhotoRow(
  photo: MediaItem,
  modifier: Modifier = Modifier,
  onPhotoClick: (id: String) -> Unit,
  onUserClick: (id: String) -> Unit,
) {
  Row(
    modifier = modifier.background(MaterialTheme.colorScheme.surface)
      .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
      .clip(RoundedCornerShape(8.dp)),
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(MaterialTheme.colorScheme.surface)
    ) {
      PostTop(
        photo.userItem.avatarUrl,
        photo.userItem.name,
        Modifier.padding(start = 4.dp, end = 4.dp)
      ) {
        onUserClick(photo.mediaInfo.creatorId)
      }
      MediaContent(
        photo.mediaInfo.mediaUrl,
        Modifier
          .fillMaxWidth()
          .padding(8.dp)
          .clickable { onPhotoClick(photo.mediaInfo.contentId) }
      )
      Text(
        modifier = Modifier
          .wrapContentHeight()
          .padding(top = 8.dp, start = 12.dp, end = 12.dp),
        text = photo.mediaInfo.thumbUrl,
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
      Text(
        modifier = Modifier
          .wrapContentHeight()
          .padding(bottom = 12.dp, start = 12.dp, end = 12.dp),
        text = photo.mediaInfo.mediaUrl,
        style = MaterialTheme.typography.bodySmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )

    }
  }
}
