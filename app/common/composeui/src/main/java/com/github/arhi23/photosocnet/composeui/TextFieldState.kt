package com.github.arhi23.photosocnet.composeui

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue

class TextFieldState(text: String = "", var errorState: ErrorState = ErrorState()) {

  var text by mutableStateOf(text)
}

val TextFieldStateSaver = Saver<TextFieldState, Any>(
  save = { arrayListOf(it.text, it.errorState.isError, it.errorState.errorMessage) },
  restore = {
    val list = it as List<Any>
    TextFieldState(list[0] as String, ErrorState(list[1] as Boolean, list[2] as Int))
  }
)

class ErrorState(isError: Boolean = false, @StringRes errorMessage: Int = 0) {
  var isError by mutableStateOf(isError)
  var errorMessage by mutableStateOf(errorMessage)
}