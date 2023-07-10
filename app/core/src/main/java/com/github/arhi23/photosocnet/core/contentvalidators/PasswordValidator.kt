package com.github.arhi23.photosocnet.core.contentvalidators

import javax.inject.Inject

class PasswordValidator @Inject constructor() {
  fun validate(text: String): Boolean {
    return text.isNotBlank()
  }
}