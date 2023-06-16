package com.github.arhi23.photosocnet.data.contentvalidators

import javax.inject.Inject

class PasswordValidator @Inject constructor() {
  fun validate(text: String): Boolean {
    return text.isNotBlank()
  }
}