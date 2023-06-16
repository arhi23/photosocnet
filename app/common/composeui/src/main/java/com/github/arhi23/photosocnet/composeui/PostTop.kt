package com.github.arhi23.photosocnet.composeui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun PostTop(
  avatarUrl: String,
  userName: String,
  modifier: Modifier = Modifier,
  onUserClick: () -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
      .clickable(onClick = onUserClick)
  ) {
    Avatar(
      avatarUrl = avatarUrl,
      modifier = modifier
        .padding(8.dp)
        .size(42.dp)
        .clip(CircleShape)
    )
    Text(
      modifier = Modifier
        .wrapContentHeight()
        .wrapContentWidth()
        .align(Alignment.Top)
        .padding(top = 8.dp, bottom = 4.dp, start = 4.dp, end = 4.dp),
      text = userName,
      style = MaterialTheme.typography.bodySmall,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis
    )
  }
}