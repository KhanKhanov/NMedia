package ru.netology.nmedia

data class Posts(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var checkLikes: Int,
    var checkShare: Int,
    var likeByMe: Boolean = false
)

fun formatNumber(number: Int): String {
    return when {
        number >= 1_000_000 -> {
            val formatNumber = number / 1_000_000.0
            if (((formatNumber * 10).toInt() % 10) == 0) {
                String.format("%.0fM", formatNumber)
            } else {
                String.format("%.1fM", formatNumber)
            }
        }
        number >= 1_000 -> {
            val formatNumber = number / 1000.0
            if (((formatNumber * 10).toInt() % 10) == 0) {
                String.format("%.0fК", formatNumber)
            } else {
                String.format("%.1fК", formatNumber)
            }
        }
        else -> String.format("%.0f", number.toDouble())
    }
}