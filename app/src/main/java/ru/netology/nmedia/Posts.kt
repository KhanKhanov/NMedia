package ru.netology.nmedia

data class Posts(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val checkLikes: Int,
    val checkShare: Int,
    val likeByMe: Boolean = false
)