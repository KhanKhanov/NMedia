package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            post?.let {
                with(binding) {
                    author.text = post.author
                    postGreeting.text = post.content
                    published.text = post.published
                    checkLikes.text = formatNumber(post.checkLikes)
                    checkShare.text = formatNumber(post.checkShare)
                    likes.setImageResource(
                        if (post.likeByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
                    )
                    checkLikes.text = formatNumber(post.checkLikes)
                    checkShare.text = formatNumber(post.checkShare)
                }
            }
        }

        binding.likes.setOnClickListener {
            viewModel.likes()
        }

        binding.share.setOnClickListener{
            viewModel.share()
        }
    }

    private fun formatNumber(number: Int): String {
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
                val formatNumber = number / 1_000.0
                if (((formatNumber * 10).toInt() % 10) == 0) {
                    String.format("%.0fK", formatNumber)
                } else {
                    String.format("%.1fK", formatNumber)
                }
            }
            else -> number.toString()
        }
    }
}
