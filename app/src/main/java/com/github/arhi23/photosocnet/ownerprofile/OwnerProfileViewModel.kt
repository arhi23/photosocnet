package com.github.arhi23.photosocnet.ownerprofile

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
import com.github.arhi23.photosocnet.core.Result.Failure
import com.github.arhi23.photosocnet.core.Result.Success
import com.github.arhi23.photosocnet.core.ServerError
import com.github.arhi23.photosocnet.data.entities.UserItemEnt
import com.github.arhi23.photosocnet.data.usecases.GetOwnerTask
import com.github.arhi23.photosocnet.data.usecases.SaveOwnerTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class OwnerProfileViewModel @Inject constructor(
  private val getOwnerTask: GetOwnerTask,
  private val saveOwnerTask: SaveOwnerTask,
  private val dispatchers: AppDispatchers
) : BaseViewModel<OwnerProfileState, UiSideEffect>(initialState = OwnerProfileState(Empty, null)) {

  private var getOwnerJob: Job? = null

  init {
    getOwner()
  }

  private fun getOwner() = intent {
    reduce { state.copy(status = Loading) }
    getOwnerJob?.cancel()
    getOwnerJob = viewModelScope.launch(dispatchers.io) {
      reduce {
        state.copy(status = Loading)
      }
      val result = getOwnerTask()
      reduce { state.copy(status = Loaded) }
      when (result) {
        is Success<UserItemEnt> -> reduce {
          state.copy(status = Loaded, userItem = result.data)
        }

        is Failure<*> -> handleError(result)
      }
    }
  }

  fun openEditPhoto() = intent {

  }

  fun saveOwner(name: String, description: String) = intent {
    reduce { state.copy(status = Loading) }
    getOwnerJob?.cancel()
    getOwnerJob = viewModelScope.launch(dispatchers.io) {
      reduce {
        state.copy(status = Loading)
      }
      val result = saveOwnerTask(SaveOwnerTask.Params(name, description))
      reduce { state.copy(status = Loaded) }
      when (result) {
        is Success<UserItemEnt> -> reduce {
          state.copy(status = Loaded, userItem = result.data)
        }

        is Failure<*> -> handleError(result)
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
        postSideEffect(ShowSnackbar((result.data as RepositoryError.SpecificError).reason))
      }

      else -> {
        postSideEffect(ShowSnackbar("Unexpected error"))
      }
    }
  }
}


data class OwnerProfileState(
  val status: UiStatus,
  val userItem: UserItemEnt? = null
)