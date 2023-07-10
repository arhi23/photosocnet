package com.github.arhi23.photosocnet.data

import com.github.arhi23.photosocnet.api.network.model.CommentInteractionNet
import com.github.arhi23.photosocnet.api.network.model.EmoteInteractionNet
import com.github.arhi23.photosocnet.api.network.model.LikeInteractionNet
import com.github.arhi23.photosocnet.api.network.model.MediaItemNet
import com.github.arhi23.photosocnet.api.network.model.MediaListResponse
import com.github.arhi23.photosocnet.api.network.model.UserItemNet
import com.github.arhi23.photosocnet.core.entities.MediaInfoEnt
import com.github.arhi23.photosocnet.core.entities.RemoteKeyEnt
import com.github.arhi23.photosocnet.core.entities.UserItemEnt

class TestData {
}

val mediaItem1 = MediaInfoEnt(
  contentId = "131415",
  mediaUrl = "https://example.com/media5.jpg",
  thumbUrl = "https://example.com/media5_thumb.jpg",
  byOwner = false,
  type = "image",
  createdAt = 1642984800,
  commentCount = 4,
  likeCount = 5,
  creatorId = "user456"
)
val mediaItem2 = MediaInfoEnt(
  contentId = "101112",
  mediaUrl = "https://example.com/media4.jpg",
  thumbUrl = "https://example.com/media4_thumb.jpg",
  byOwner = false,
  type = "image",
  createdAt = 1642984200,
  commentCount = 0,
  likeCount = 0,
  creatorId = "user101112"
)
val mediaItem3 = MediaInfoEnt(
  contentId = "789",
  mediaUrl = "https://example.com/media3.jpg",
  thumbUrl = "https://example.com/media3_thumb.jpg",
  byOwner = false,
  type = "image",
  createdAt = 1642983600,
  commentCount = 0,
  likeCount = 3,
  creatorId = "user789"
)
val mediaItem4 = MediaInfoEnt(
  contentId = "123",
  mediaUrl = "https://example.com/media1.jpg",
  thumbUrl = "https://example.com/media1_thumb.jpg",
  byOwner = false,
  type = "image",
  createdAt = 1642982400,
  commentCount = 5,
  likeCount = 0,
  creatorId = "user123"
)
val mediaItem5 = MediaInfoEnt(
  contentId = "456",
  mediaUrl = "https://example.com/media2.jpg",
  thumbUrl = "https://example.com/media2_thumb.jpg",
  byOwner = false,
  type = "image",
  createdAt = 1642983000,
  commentCount = 2,
  likeCount = 20,
  creatorId = "user131415"
)

val userItem1 = UserItemEnt(
  uid = "user123",
  name = "John Doe",
  isOwner = false,
  description = "I'm a software engineer",
  avatarUrl = "https://example.com/avatar1.jpg",
  serverId = "user123"
)
val userItem2 = UserItemEnt(
  uid = "user131415",
  name = "Alex Lee",
  isOwner = false,
  description = "I'm a marketer",
  avatarUrl = "https://example.com/avatar5.jpg",
  serverId = "user131415"
)
val userItem3 = UserItemEnt(
  uid = "user789",
  name = "Mike Johnson",
  isOwner = false,
  description = "I'm a photographer",
  avatarUrl = "https://example.com/avatar3.jpg",
  serverId = "user789"
)
val userItem4 = UserItemEnt(
  uid = "user101112",
  name = "Emily Brown",
  isOwner = false,
  description = "I'm a writer",
  avatarUrl = "https://example.com/avatar4.jpg",
  serverId = "user101112"
)
val userItem5 = UserItemEnt(
  uid = "user456",
  name = "Jane Smith",
  isOwner = false,
  description = "I'm a graphic designer",
  avatarUrl = "https://example.com/avatar2.jpg",
  serverId = "user456"
)

val mediaItems = listOf(mediaItem1, mediaItem2, mediaItem3, mediaItem4, mediaItem5)
val userItems = listOf(userItem1, userItem2, userItem3, userItem4, userItem5)
val remoteKeyEnt = RemoteKeyEnt("media", "3993")

val mediaNet1 = MediaItemNet(
  "https://example.com/media5.jpg",
  "https://example.com/media5_thumb.jpg",
  "131415",
  "user456",
  "image",
  false,
  false,
  1642984800,
  3,
  8
)
val mediaNet2 = MediaItemNet(
  "https://example.com/media4.jpg",
  "https://example.com/media4_thumb.jpg",
  "101112",
  "user101112",
  "image",
  false,
  false,
  1642984200,
  0,
  5
)
val mediaNet3 = MediaItemNet(
  "https://example.com/media3.jpg",
  "https://example.com/media3_thumb.jpg",
  "789",
  "user789",
  "image",
  false,
  false,
  1642983600,
  8,
  15
)
val mediaNet4 = MediaItemNet(
  "https://example.com/media1.jpg",
  "https://example.com/media1_thumb.jpg",
  "123",
  "user123",
  "image",
  false,
  false,
  1642982400,
  5,
  10
)
val mediaNet5 = MediaItemNet(
  "https://example.com/media2.jpg",
  "https://example.com/media2_thumb.jpg",
  "456",
  "user131415",
  "image",
  false,
  false,
  1642983000,
  2,
  20
)

val userNet1 = UserItemNet(
  "user123", "John Doe", false, "I'm a software engineer", "https://example.com/avatar1.jpg",
)
val userNet2 = UserItemNet(
  "user131415", "Alex Lee", false, "I'm a marketer", "https://example.com/avatar5.jpg",
)
val userNet3 = UserItemNet(
  "user789", "Mike Johnson", false, "I'm a photographer", "https://example.com/avatar3.jpg",
)
val userNet4 = UserItemNet(
  "user101112", "Emily Brown", false, "I'm a writer", "https://example.com/avatar4.jpg",
)
val userNet5 = UserItemNet(
  "user456",
  "Jane Smith",
  false,
  "I'm a graphic designer",
  "https://example.com/avatar2.jpg",
)
val user1 =
  UserItemNet(
    "user11",
    "John Doe",
    true,
    "Software UI designer engineer",
    "https://example.com/avatar1.jpg"
  )
val user2 =
  UserItemNet(
    "user32",
    "Jane Smith",
    false,
    "United Interface designer",
    "https://example.com/avatar2.jpg"
  )
val user3 =
  UserItemNet(
    "user23",
    "Mike Johnson",
    false,
    "Marketing specialist",
    "https://example.com/avatar3.jpg"
  )
val user4 =
  UserItemNet(
    "user64",
    "Linda Davis",
    false,
    "Content provider writer",
    "https://example.com/avatar4.jpg"
  )
val user5 =
  UserItemNet(
    "user95",
    "Bob Anderson",
    false,
    "Data generator analyst",
    "https://example.com/avatar5.jpg"
  )
val user6 =
  UserItemNet(
    "user46",
    "Mary Lee",
    false,
    "Graphic office support designer",
    "https://example.com/avatar6.jpg"
  )
val user7 = UserItemNet(
  "user57",
  "David Brown",
  false,
  "Product best office manager",
  "https://example.com/avatar7.jpg"
)
val user8 = UserItemNet(
  "user98",
  "Samantha Wilson",
  false,
  "Customer best support",
  "https://example.com/avatar8.jpg"
)
val user9 = UserItemNet(
  "user79",
  "Tom White",
  false,
  "Web great developer",
  "https://example.com/avatar9.jpg"
)
val user10 = UserItemNet(
  "user80",
  "Emily Black",
  false,
  "Social media specialist",
  "https://example.com/avatar10.jpg"
)

val emoteInteraction1 = EmoteInteractionNet("1", "Amazing!", false, 10, "user123", false, "131415")
val emoteInteraction2 = EmoteInteractionNet("2", "Cool!", false, 5, "user2", false, "content2")
val emoteInteraction3 = EmoteInteractionNet("3", "Awesome!", false, 20, "user3", false, "content3")
val emoteInteraction4 = EmoteInteractionNet("4", "Nice!", false, 15, "user4", false, "content4")
val emoteInteraction5 = EmoteInteractionNet("5", "Great!", false, 7, "user5", false, "content5")

val commentInteraction1 = CommentInteractionNet(
  "1",
  "Great post!",
  1647686523L,
  "user1",
  false,
  false,
  5,
  "131415",
  10,
  "user123"
)
val commentInteraction2 = CommentInteractionNet(
  "2",
  "I agree with you!",
  1647686821L,
  "user3",
  false,
  false,
  3,
  "131415",
  8,
  "user123"
)
val commentInteraction3 = CommentInteractionNet(
  "3",
  "Interesting point of view!",
  1647687034L,
  "user5",
  false,
  false,
  2,
  "user6",
  3,
  "content3"
)
val commentInteraction4 = CommentInteractionNet(
  "4",
  "I have a different opinion.",
  1647687288L,
  "user7",
  false,
  false,
  7,
  "user8",
  4,
  "content4"
)
val commentInteraction5 = CommentInteractionNet(
  "5",
  "Thanks for sharing!",
  1647687555L,
  "user9",
  false,
  false,
  1,
  "user10",
  2,
  "content5"
)
val likeInteraction1 = LikeInteractionNet("1", false, false, "131415", "user123")
val likeInteraction2 = LikeInteractionNet("2", false, false, "131415", "user98")
val likeInteraction3 = LikeInteractionNet("3", false, false, "131415", "user57")

val likeInteraction4 = LikeInteractionNet("4", false, false, "789", "user46")
val likeInteraction5 = LikeInteractionNet("5", false, false, "789", "user23")
val likeInteraction6 = LikeInteractionNet("6", false, false, "789", "user80")

val likeInteraction7 = LikeInteractionNet("7", false, false, "456", "user79")
val likeInteraction8 = LikeInteractionNet("7", false, false, "456", "user46")

val mediasNet = listOf(mediaNet1, mediaNet2, mediaNet3, mediaNet4, mediaNet5)
val usersNet = listOf(userNet1, userNet2, userNet3, userNet4, userNet5)
val likesNet = listOf(
  likeInteraction1,
  likeInteraction2,
  likeInteraction3,
  likeInteraction4,
  likeInteraction5,
  likeInteraction6,
  likeInteraction7,
  likeInteraction8
)
val emotesNet = listOf<EmoteInteractionNet>()
val commentsNet = listOf(
  commentInteraction1,
  commentInteraction2,
  commentInteraction3,
  commentInteraction4,
  commentInteraction5
)
val response = MediaListResponse(mediasNet, likesNet, commentsNet, emotesNet, usersNet, "123")