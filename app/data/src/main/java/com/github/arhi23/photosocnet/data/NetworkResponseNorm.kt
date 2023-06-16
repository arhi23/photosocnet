package com.github.arhi23.photosocnet.data

import com.github.arhi23.photosocnet.api.network.model.CommentInteractionNet
import com.github.arhi23.photosocnet.api.network.model.EmoteInteractionNet
import com.github.arhi23.photosocnet.api.network.model.LikeInteractionNet
import com.github.arhi23.photosocnet.api.network.model.MediaItemNet
import com.github.arhi23.photosocnet.api.network.model.UserItemNet
import com.github.arhi23.photosocnet.data.daos.CommentDao
import com.github.arhi23.photosocnet.data.daos.EmoteDao
import com.github.arhi23.photosocnet.data.daos.LikeDao
import com.github.arhi23.photosocnet.data.daos.MediaItemDao
import com.github.arhi23.photosocnet.data.daos.UserItemDao
import com.github.arhi23.photosocnet.data.mappers.CommentMapper
import com.github.arhi23.photosocnet.data.mappers.EmoteMapper
import com.github.arhi23.photosocnet.data.mappers.LikeMapper
import com.github.arhi23.photosocnet.data.mappers.MediaItemMapper
import com.github.arhi23.photosocnet.data.mappers.UserMapper
import javax.inject.Inject

class NetworkResponseNorm @Inject constructor(
  private val mediaResponseSaver: MediaResponseSaver,
  private val likeResponseSaver: LikeResponseSaver,
  private val commentResponseSaver: CommentResponseSaver,
  private val emoteResponseSaver: EmoteResponseSaver,
  private val userResponseSaver: UserResponseSaver
) {
  suspend fun mapsaveMedias(items: List<MediaItemNet>) {
    mediaResponseSaver.work(items)
  }
  suspend fun mapsaveLikes(items: List<LikeInteractionNet>) {
    likeResponseSaver.work(items)
  }
  suspend fun mapsaveComments(items: List<CommentInteractionNet>) {
    commentResponseSaver.work(items)
  }
  suspend fun mapsaveEmotes(items: List<EmoteInteractionNet>) {
    emoteResponseSaver.work(items)
  }
  suspend fun mapsaveUsers(items: List<UserItemNet>) {
    userResponseSaver.work(items)
  }
  suspend fun mapsaveUser(item: UserItemNet) {
    userResponseSaver.work(listOf(item))
  }
}

class MediaResponseSaver @Inject constructor(private val mediaItemDao: MediaItemDao, private val mediaItemMapper: MediaItemMapper) {
  suspend fun work(items: List<MediaItemNet>) {
    val medias = items.map { mediaItemMapper.map(it) }
    mediaItemDao.insertAll(medias)
  }
}

class LikeResponseSaver @Inject constructor(private val likeDao: LikeDao, private val likeMapper: LikeMapper) {
  suspend fun work(items: List<LikeInteractionNet>) {
    val likes = items.map { likeMapper.map(it) }
    likeDao.insertAll(likes)
  }
}

class CommentResponseSaver @Inject constructor(private val commentDao: CommentDao, private val commentMapper: CommentMapper) {
  suspend fun work(items: List<CommentInteractionNet>) {
    val comments = items.map { commentMapper.map(it) }
    commentDao.insertAll(comments)
  }
}

class EmoteResponseSaver @Inject constructor(private val emoteDao: EmoteDao, private val emoteMapper: EmoteMapper) {
  suspend fun work(items: List<EmoteInteractionNet>) {
    val emotes = items.map { emoteMapper.map(it) }
    emoteDao.insertAll(emotes)
  }
}

class UserResponseSaver @Inject constructor(private val userItemDao: UserItemDao, private val userMapper: UserMapper) {
  suspend fun work(items: List<UserItemNet>) {
    val users = items.map { userMapper.map(it) }
    userItemDao.insertAll(users)
  }
}