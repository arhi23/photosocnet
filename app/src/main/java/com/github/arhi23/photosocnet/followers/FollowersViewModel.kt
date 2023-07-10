package com.github.arhi23.photosocnet.followers

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.arhi23.photosocnet.BaseViewModel
import com.github.arhi23.photosocnet.UiSideEffect
import com.github.arhi23.photosocnet.UiStatus
import com.github.arhi23.photosocnet.UiStatus.Empty
import com.github.arhi23.photosocnet.core.entities.MediaItem
import com.github.arhi23.photosocnet.core.entities.UserItemEnt
import com.github.arhi23.photosocnet.core.sources.FollowersRemoteDataSource
import com.github.arhi23.photosocnet.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.syntax.simple.intent
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val followersDataSource: FollowersRemoteDataSource,
) : BaseViewModel<FollowersState, UiSideEffect>(initialState = FollowersState(Empty)) {

  private val userId = savedStateHandle.navArgs<FollowersNavParams>().userId

  val lazyPagingItems: Flow<PagingData<UserItemEnt>> = followersDataSource.getFollowers().cachedIn()

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

