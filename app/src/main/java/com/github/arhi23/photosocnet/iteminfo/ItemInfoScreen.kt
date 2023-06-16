package com.github.arhi23.photosocnet.iteminfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.arhi23.photosocnet.UiStatus.Loaded
import com.github.arhi23.photosocnet.composeui.PostTop
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.saket.telephoto.zoomable.coil.ZoomableAsyncImage
import org.orbitmvi.orbit.compose.collectAsState

@Preview
@Composable
fun prr() {
//  MainImage()
}

@Destination(
  navArgsDelegate = MediaFullNavParams::class
)
@Composable
fun ItemInfoScreen(
  viewModel: ItemInfoViewModel = hiltViewModel(),
  navParams: MediaFullNavParams,
  destinationsNavigator: DestinationsNavigator) {
  val state = viewModel.collectAsState()
  val onUserClick = remember { { userId: String -> viewModel.onUserClick(userId) } }
  Column(Modifier.padding(8.dp)) {
    when(state.value.status) {
      is Loaded -> {
        state.value.mediaItem?.let {
          PostTop(avatarUrl = it.userItem.avatarUrl, it.userItem.name) { onUserClick(it.mediaInfo.creatorId) }
          Spacer(modifier = Modifier.height(16.dp))
          ZoomableAsyncImage(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            contentScale = ContentScale.FillBounds,
            model = it.mediaInfo.mediaUrl,
            contentDescription = "fullpost"
          )
        }
      }
    }
  }
}