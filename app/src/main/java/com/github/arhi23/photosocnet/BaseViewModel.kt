package com.github.arhi23.photosocnet

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseViewModel<S: Any, E: Any>(initialState: S) : ViewModel(), ContainerHost<S, E> {

  override val container =
    container<S, E>(initialState = initialState)
}