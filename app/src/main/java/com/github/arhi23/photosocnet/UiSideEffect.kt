package com.github.arhi23.photosocnet

interface UiSideEffect {
  object NoEffect: UiSideEffect
  class ShowSnackbar(val text: String, val action: (() -> Unit)? = null, var used: Boolean = false): UiSideEffect
}