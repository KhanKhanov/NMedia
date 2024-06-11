package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface PostRepository {
    fun get(): LiveData<Posts?>
    fun likes()
    fun share()
}

class PostRepositoryInMemoryImpl: PostRepository {
    private var post = Posts(
        id = 1,
        author = "Нетология. Универсиитет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netology.ru/",
        published = "21 may in 18:30",
        checkLikes = 1099999,
        checkShare = 998,
        likeByMe = false
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Posts?> = data

    override fun likes() {
        post = post.copy(
            likeByMe = !post.likeByMe,
            checkLikes = if (post.likeByMe) post.checkLikes - 1 else post.checkLikes + 1
        )
        data.postValue(post)
    }

    override fun share() {
        post = post.copy(
            checkShare = post.checkShare + 1
        )
        data.postValue(post)
    }


}

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val data = repository.get()
    fun likes() = repository.likes()
    fun share() = repository.share()
}

