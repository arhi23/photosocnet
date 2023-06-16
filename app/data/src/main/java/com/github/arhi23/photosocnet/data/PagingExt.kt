package com.github.arhi23.photosocnet.data

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun CombinedLoadStates.appendErrorOrNull(): Throwable? {
  return (append.takeIf { it is LoadState.Error } as? LoadState.Error)?.error
}

fun CombinedLoadStates.prependErrorOrNull(): Throwable? {
  return (prepend.takeIf { it is LoadState.Error } as? LoadState.Error)?.error
}

fun CombinedLoadStates.refreshErrorOrNull(): Throwable? {
  return (refresh.takeIf { it is LoadState.Error } as? LoadState.Error)?.error
}
