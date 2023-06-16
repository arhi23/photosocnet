package com.github.arhi23.photosocnet.photolist

import androidx.paging.PagingData
import com.github.arhi23.photosocnet.BaseViewModel
import com.github.arhi23.photosocnet.UiSideEffect
import com.github.arhi23.photosocnet.UiSideEffect.ShowSnackbar
import com.github.arhi23.photosocnet.UiStatus
import com.github.arhi23.photosocnet.UiStatus.Empty
import com.github.arhi23.photosocnet.data.entities.MediaItem
import com.github.arhi23.photosocnet.data.repositories.MediaDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import javax.inject.Inject

@HiltViewModel
class PhotolistViewModel @Inject constructor(
  private val photoDataSource: MediaDataSource,
) : BaseViewModel<PhototsListState, UiSideEffect>(initialState = PhototsListState(Empty)) {

  val lazyPagingItems: Flow<PagingData<MediaItem>> = photoDataSource.getMedia()

  fun openPhoto(itemId: String) = intent {
    photoDataSource.getMedia()
  }

  fun openUser(itemId: String) = intent {
    postSideEffect(ShowSnackbar("jji"))
  }

}

data class PhototsListState(
  val status: UiStatus,
  val photos: List<MediaItem> = emptyList()
)
