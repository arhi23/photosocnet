package com.github.arhi23.photosocnet.followers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.github.arhi23.photosocnet.UiSideEffect
import com.github.arhi23.photosocnet.UiStatus
import com.github.arhi23.photosocnet.UiStatus.Empty
import com.github.arhi23.photosocnet.data.entities.MediaItem
import com.github.arhi23.photosocnet.data.entities.UserItemEnt
import com.github.arhi23.photosocnet.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val followersDataSource: FollowersDataSource,
) : ViewModel(),
  ContainerHost<FollowersState, UiSideEffect> {

  private val userId = savedStateHandle.navArgs<FollowersNavParams>().userId

  override val container =
    container<FollowersState, UiSideEffect>(initialState = FollowersState(Empty))

  val lazyPagingItems: Flow<PagingData<UserItemEnt>> = followersDataSource.getFollowers()

  init {
    refresh(userId)
  }

  fun loadMore() = intent {

  }

  fun refresh(userId: String) = intent {

  }

  fun openItem(itemId: String) = intent {

  }

  fun removeFollower(itemId: String) = intent {

  }

  fun openUser(it: String) {

  }

}

data class FollowersState(val status: UiStatus, val photos: List<MediaItem>? = null)

