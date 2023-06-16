package com.github.arhi23.photosocnet.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.arhi23.photosocnet.resources.R.string
import com.github.arhi23.photosocnet.R.drawable
import com.github.arhi23.photosocnet.UiSideEffect
import com.github.arhi23.photosocnet.UiStatus.Empty
import com.github.arhi23.photosocnet.UiStatus.Loaded
import com.github.arhi23.photosocnet.UiStatus.Loading
import com.github.arhi23.photosocnet.UiStatus.NoInternet
import com.github.arhi23.photosocnet.UiStatus.ServerProblem
import com.github.arhi23.photosocnet.composeui.ErrorState
import com.github.arhi23.photosocnet.composeui.TextFieldState
import com.github.arhi23.photosocnet.composeui.TextFieldStateSaver
import com.github.arhi23.photosocnet.composeui.theme.PsnTheme
import com.github.arhi23.photosocnet.login.LoginScreenSideEffect.InvalidLogin
import com.github.arhi23.photosocnet.login.LoginScreenSideEffect.InvalidPassword
import com.github.arhi23.photosocnet.login.LoginScreenSideEffect.OpenFeed
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
@Preview(name = "LoginScreen", showBackground = true)
fun JetKiteButtonPreview() {
  PsnTheme {
//    Login()
  }
}

//todo focus request order appear transition


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun Login(
  viewModel: LoginViewModel = hiltViewModel(),
  navigator: DestinationsNavigator
) {
  val state by viewModel.collectAsState()
  val login = rememberSaveable(saver = TextFieldStateSaver) { TextFieldState() }
  val password = rememberSaveable(saver = TextFieldStateSaver) { TextFieldState() }
  val coroutineScope = rememberCoroutineScope()
  val snackbarHostState = remember { SnackbarHostState() }

  val onLoginClick = remember { { viewModel.performLogin(login.text, password.text) } }

  viewModel.collectSideEffect {
      when (it) {
        is OpenFeed -> {
        }
        is InvalidLogin -> {
          login.errorState.isError = true
          login.errorState.errorMessage = string.error
        }

        is InvalidPassword -> {
          password.errorState.isError = true
          password.errorState.errorMessage = string.error
        }

        is UiSideEffect.ShowSnackbar -> {
          coroutineScope.launch {
            snackbarHostState.showSnackbar(it.toString())
          }
        }

    }
  }

  Scaffold(
    contentWindowInsets = WindowInsets.statusBars,
    modifier = Modifier,
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState) { data ->
        Snackbar(snackbarData = data)
      }
    },
    content = { innerPadding ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        when (state.status) {
          is Empty -> {
            AppLogo(Modifier.size(32.dp))
            LoginFields(login, password, onLoginClick)
          }

          is NoInternet -> {
            AppLogo(Modifier.size(32.dp))
            LoginFields(login, password, onLoginClick)
          }

          is Loading -> {
            LoadingBar(Modifier.size(32.dp))
            LoginFields(login, password, onLoginClick)
          }

          is Loaded -> {
            AppLogo(Modifier.size(32.dp))
            LoginFields(login, password, onLoginClick)
          }

          is ServerProblem -> {
            AppLogo(Modifier.size(32.dp))
            LoginFields(login, password, onLoginClick)
          }
        }
      }
    })
}

@Composable
private fun LoginFields(
  login: TextFieldState,
  password: TextFieldState,
  onLoginClick: () -> Unit
) {
  Spacer(modifier = Modifier.height(16.dp))
  LoginField(login)
  Spacer(modifier = Modifier.height(8.dp))
  PasswordField(password)
  Spacer(modifier = Modifier.height(16.dp))
  LoginButton(onLoginClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginField(login: TextFieldState) {
  TextField(
    value = login.text,
    onValueChange = {
      login.text = it
      if (login.errorState.isError) {
        login.errorState = ErrorState()
      }
                    },
    label = { Text(stringResource(id = string.login)) },
    isError = login.errorState.isError,
    supportingText = {
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = login.errorState.errorMessage),
        color = MaterialTheme.colorScheme.error
      )
    },
    trailingIcon = { Icon(imageVector = Icons.Filled.PermIdentity, stringResource(id = string.login)) }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: TextFieldState) {
  var passwordVisible by rememberSaveable { mutableStateOf(false) }

  TextField(
    value = password.text,
    onValueChange = {
      password.text = it
      if (password.errorState.isError) {
        password.errorState = ErrorState()
      }
    },
    singleLine = true,
    label = { Text(stringResource(id = string.password)) },
    supportingText = {
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = password.errorState.errorMessage),
        color = MaterialTheme.colorScheme.error
      )
    },
    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    trailingIcon = {
      val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
      val description = if (passwordVisible) stringResource(id = string.hide_password) else stringResource(id = string.show_password)

      IconButton(onClick = { passwordVisible = !passwordVisible }) {
        Icon(imageVector = image, description)
      }
    },
  )
}

@Composable
fun LoginButton(onClick: () -> Unit) {
  Button(
    onClick = onClick
  )
  {
    Text(text = stringResource(id = string.hide_password))
  }
}

@Composable
fun NoInternet() {
  Text(text = stringResource(id = string.no_internet))
}

@Composable
fun AppLogo(modifier: Modifier = Modifier) {
  Image(
    painter = painterResource(id = drawable.app_t),
    contentDescription = stringResource(id = string.app_Logo),
    contentScale = ContentScale.Crop,
    modifier = modifier
  )
}

@Composable
fun LoadingBar(modifier: Modifier = Modifier) {
  CircularProgressIndicator(
    modifier = modifier
      .progressSemantics()
  )
}
