package com.github.arhi23.photosocnet.login

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
import com.github.arhi23.photosocnet.core.contentvalidators.LoginValidator
import com.github.arhi23.photosocnet.core.contentvalidators.PasswordValidator
import com.github.arhi23.photosocnet.core.usecases.LoginTask
import com.github.arhi23.photosocnet.core.usecases.LoginTask.Params
import com.github.arhi23.photosocnet.login.LoginScreenSideEffect.InvalidLogin
import com.github.arhi23.photosocnet.login.LoginScreenSideEffect.InvalidPassword
import com.github.arhi23.photosocnet.login.LoginScreenSideEffect.OpenFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val loginTask: LoginTask,
  private val dispatchers: AppDispatchers,
  private val loginValidator: LoginValidator,
  private val passwordValidator: PasswordValidator
) : BaseViewModel<LoginScreenState, UiSideEffect>(initialState = LoginScreenState(Empty)) {

  private var loginJob: Job? = null

  fun performLogin(login: String, password: String) = intent {
    if (!loginValidator.validate(login) || !passwordValidator.validate(login)) {
      if (!loginValidator.validate(login)) {
      postSideEffect(InvalidLogin)
    }
    if (!passwordValidator.validate(login)) {
      postSideEffect(InvalidPassword)
    }
      return@intent
  }
    loginJob?.cancel()
    loginJob = viewModelScope.launch(dispatchers.io) {
      reduce {
        state.copy(status = Loading)
      }
      val result = loginTask(Params(login, password))
      when (result) {
        is Success<*> -> {
          reduce {
            state.copy(status = Loaded)
          }
          postSideEffect(OpenFeed)
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

