package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Posts(
            id = 1,
            author = "Нетология. Универсиитет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netology.ru/",
            published = "21 may in 18:30",
            checkLikes = 1099999,
            checkShare = 998,
            likeByMe = false
        )

        with(binding) {
            author.text = post.author
            postGreeting.text = post.content
            published.text = post.published
            checkLikes.text = formatNumber(post.checkLikes)
            checkShare.text = formatNumber(post.checkShare)
            if (post.likeByMe) {
                likes.setImageResource(R.drawable.baseline_favorite_24)

            }
            likes.setOnClickListener {
                if (post.likeByMe) {
                    post.likeByMe = false
                    post.checkLikes--
                } else {
                    post.likeByMe = true
                    post.checkLikes++
                }

                likes.setImageResource(
                    if(post.likeByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
                )

                checkLikes.text = formatNumber(post.checkLikes)
            }

            share.setOnClickListener {
                post.checkShare++
                checkShare.text = formatNumber(post.checkShare)
            }


        }


    }
}