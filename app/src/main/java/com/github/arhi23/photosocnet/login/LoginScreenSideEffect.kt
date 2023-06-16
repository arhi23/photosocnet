package com.github.arhi23.photosocnet.login

import com.github.arhi23.photosocnet.UiSideEffect

sealed class LoginScreenSideEffect: UiSideEffect {
  object OpenFeed: LoginScreenSideEffect()
  object InvalidLogin: LoginScreenSideEffect()
  object InvalidPassword: LoginScreenSideEffect()
}