package com.github.arhi23.photosocnet.data.entities

data class SocialInteractions(
 val contentId: String,
 val likes: List<LikeItem>,
 val comments: List<CommentItem>,
 val emotes: List<EmoteItem>
)