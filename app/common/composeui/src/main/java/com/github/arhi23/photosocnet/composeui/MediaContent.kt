package com.github.arhi23.photosocnet.composeui

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.github.arhi23.photosocnet.resources.R.string

@Composable
fun MediaContent(url: String, modifier: Modifier = Modifier) {
  AsyncImage(
    contentScale = ContentScale.Crop,
    modifier = modifier.clip(CircleShape),
    placeholder = rememberVectorPainter(image = Filled.BrokenImage),
    error = rememberVectorPainter(image = Filled.BrokenImage),
    model = Builder(LocalContext.current)
      .data(url)
      .crossfade(true)
      .build(),
    contentDescription = stringResource(id = string.media_content)
  )
}