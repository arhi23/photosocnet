package com.github.arhi23.photosocnet.userprofile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.arhi23.photosocnet.BaseViewModel
import com.github.arhi23.photosocnet.UiSideEffect
import com.github.arhi23.photosocnet.UiSideEffect.ShowSnackbar
import com.github.arhi23.photosocnet.UiStatus
import com.github.arhi23.photosocnet.UiStatus.Empty
import com.github.arhi23.photosocnet.UiStatus.Loaded
import com.github.arhi23.photosocnet.UiStatus.Loading
import com.github.arhi23.photosocnet.UiStatus.NoInternet
import com.github.arhi23.photosocnet.UiStatus.ServerProblem
import com.github.arhi23.photosocnet.core.AppDispatchers
import com.github.arhi23.photosocnet.core.RepositoryError
import com.github.arhi23.photosocnet.core.Result.Failure
import com.github.arhi23.photosocnet.core.Result.Success
import com.github.arhi23.photosocnet.core.ServerError
import com.github.arhi23.photosocnet.data.entities.UserItemEnt
import com.github.arhi23.photosocnet.data.usecases.GetUserTask
import com.github.arhi23.photosocnet.data.usecases.GetUserTask.Params
import com.github.arhi23.photosocnet.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
  private val getUserUsecase: GetUserTask,
  private val dispatchers: AppDispatchers,
  savedStateHandle: SavedStateHandle,
) : BaseViewModel<UserProfileState, UiSideEffect>(UserProfileState(Empty)) {

  private val userId = savedStateHandle.navArgs<UserProfileNavParams>().userId

  private var getUserJob: Job? = null

  init {
    getUser(userId)
  }

  fun openFollowers(itemId: String) = intent {
  }

  fun getUser(userId: String) = intent {
    getUserJob?.cancel()
    getUserJob = viewModelScope.launch(dispatchers.io) {
      reduce {
        state.copy(status = Loading)
      }
      try {
        val result = getUserUsecase(Params(userId))
        when (result) {
          is Success -> reduce {
            state.copy(
              status = Loaded,
              userItem = result.data
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
        reduce { state.copy(status = NoInternet) }
        postSideEffect(ShowSnackbar(""))
      }

      is ServerError -> {
        reduce { state.copy(status = ServerProblem) }
        postSideEffect(ShowSnackbar(""))
      }

      else -> {

      }
    }
  }
}

data class UserProfileState(
  val status: UiStatus,
  val userItem: UserItemEnt? = null
)
