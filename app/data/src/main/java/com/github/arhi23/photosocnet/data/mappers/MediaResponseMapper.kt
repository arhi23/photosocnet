//package com.github.arhi23.photosocnet.photosocnet.data.mappers
//
//import com.github.arhi23.photosocnet.network.model.CommentInteractionNet
//import com.github.arhi23.photosocnet.network.model.EmoteInteractionNet
//import com.github.arhi23.photosocnet.network.model.LikeInteractionNet
//import com.github.arhi23.photosocnet.network.model.MediaItemNet
//import com.github.arhi23.photosocnet.photosocnet.api.network.model.MediaListResponse
//import com.github.arhi23.photosocnet.network.model.UserItemNet
//import com.github.arhi23.photosocnet.storage.db.model.UserItemEnt
//import com.github.arhi23.photosocnet.storage.db.model.photo.CommentInfoEnt
//import com.github.arhi23.photosocnet.storage.db.model.photo.EmoteInfoEnt
//import com.github.arhi23.photosocnet.storage.db.model.photo.LikeInfoEnt
//import com.github.arhi23.photosocnet.storage.db.model.photo.MediaInfoEnt
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.adapter
//import javax.inject.Inject
//
//@OptIn(ExperimentalStdlibApi::class)
//class MediaResponseMapper @Inject constructor(private val moshi: Moshi) {
//  fun fromJson(json: String): MediaListResponse {
//    val jsonAdapter = moshi.adapter<MediaListResponse>()
//    val map = jsonAdapter.fromJson(json)
//    return map
//  }
//}
//
//
