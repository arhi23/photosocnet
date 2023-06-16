package com.github.arhi23.photosocnet.iteminfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.arhi23.photosocnet.BaseViewModel
import com.github.arhi23.photosocnet.UiSideEffect
import com.github.arhi23.photosocnet.UiSideEffect.ShowSnackbar
import com.github.arhi23.photosocnet.UiStatus
import com.github.arhi23.photosocnet.UiStatus.Empty
import com.github.arhi23.photosocnet.UiStatus.Loaded
import com.github.arhi23.photosocnet.UiStatus.Loading
import com.github.arhi23.photosocnet.core.AppDispatchers
import com.github.arhi23.photosocnet.core.RepositoryError
import com.github.arhi23.photosocnet.core.RepositoryError.SpecificError
import com.github.arhi23.photosocnet.core.Result.Failure
import com.github.arhi23.photosocnet.core.Result.Success
import com.github.arhi23.photosocnet.core.ServerError
import com.github.arhi23.photosocnet.data.entities.MediaItem
import com.github.arhi23.photosocnet.data.usecases.GetMediaItemTask
import com.github.arhi23.photosocnet.data.usecases.GetMediaItemTask.Params
import com.github.arhi23.photosocnet.iteminfo.ItemInfoViewModel.ItemInfoState
import com.github.arhi23.photosocnet.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class ItemInfoViewModel @Inject constructor(
  private val getMediaItemTask: GetMediaItemTask,
  private val dispatchers: AppDispatchers,
  savedStateHandle: SavedStateHandle,
) : BaseViewModel<ItemInfoState, UiSideEffect>(ItemInfoState(Empty)) {

  private val mediaId = savedStateHandle.navArgs<MediaFullNavParams>().mediaId

  private var getMediaItemJob: Job? = null

  init {
    getMediaItem(mediaId)
  }

  fun getMediaItem(mediaId: String) = intent {
    getMediaItemJob?.cancel()
    getMediaItemJob = viewModelScope.launch(dispatchers.io) {
      reduce {
        state.copy(status = Loading)
      }
      try {
        val result = getMediaItemTask(Params(mediaId))
        when (result) {
          is Success -> reduce {
            state.copy(
              status = Loaded,
              mediaItem = result.data
            )
          }
          is Failure -> handleError(result)
        }
      } catch (e: Error) {
      }
    }
  }

  private fun handleError(result: Failure<*>) = intent {
    when (result.data) {
      is RepositoryError.NoInternet -> {
        reduce { state.copy(status = UiStatus.NoInternet) }
        postSideEffect(ShowSnackbar(""))
      }

      is ServerError -> {
        reduce { state.copy(status = UiStatus.ServerProblem) }
        postSideEffect(ShowSnackbar(""))
      }

      is RepositoryError.SpecificError -> {
        postSideEffect(ShowSnackbar((result.data as SpecificError).reason))
      }

      else -> {
        postSideEffect(ShowSnackbar("Unexpected error"))
      }
    }
  }

  fun onUserClick(userId: Any) = intent {

  }

  data class ItemInfoState(val status: UiStatus, val mediaItem: MediaItem? = null)
}