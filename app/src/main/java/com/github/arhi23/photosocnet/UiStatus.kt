package com.github.arhi23.photosocnet

interface UiStatus {
  object Refreshing : UiStatus
  object Loading : UiStatus
  object NoInternet : UiStatus
  object ServerProblem : UiStatus
  object Empty : UiStatus
  object Loaded : UiStatus
}