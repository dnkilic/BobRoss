package com.example.bobross

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bobross.repository.model.*
import org.junit.Rule
import org.junit.Test
import org.hamcrest.MatcherAssert
import org.hamcrest.CoreMatchers

@RunWith(AndroidJUnit4::class)
class PostDataDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun checkDbNotNull() {
        MatcherAssert.assertThat(db, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(db.postDao(), CoreMatchers.notNullValue())
    }

    @Test
    fun insertAndReadPost() {
        // Act
        db.postDao().insertPost(POST.copy(id = "1", width = 0, height = 0, color = "foo"))
        val queried = db.postDao().getPost("1")

        // Verify
        MatcherAssert.assertThat(queried, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(queried.width, CoreMatchers.`is`(0))
        MatcherAssert.assertThat(queried.height, CoreMatchers.`is`(0))
        MatcherAssert.assertThat(queried.color, CoreMatchers.`is`("foo"))
    }

    @Test
    fun updatePost() {
        // Act
        db.postDao().insertPost(POST.copy(id = "2", width = 0, height = 0, color = "foo"))
        db.postDao().insertPost(POST.copy(id = "2", width = 5, height = 5, color = "bar"))
        val queried = db.postDao().getPost("2")

        // Verify
        MatcherAssert.assertThat(queried, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(queried.height, CoreMatchers.`is`(5))
        MatcherAssert.assertThat(queried.color, CoreMatchers.`is`("bar"))
        MatcherAssert.assertThat(queried.width, CoreMatchers.`is`(5))
    }

    @Test
    fun deletePost() {
        // Act
        db.postDao().insertPost(POST.copy(id = "10"))
        db.postDao().deletePostById("10")
        val queriedFirst = db.postDao().getPost("10")

        val post = POST.copy(id = "9")
        db.postDao().insertPost(post)
        db.postDao().deletePost(post)
        val queriedSecond = db.postDao().getPost("9")

        // Verify
        MatcherAssert.assertThat(queriedFirst, CoreMatchers.nullValue())
        MatcherAssert.assertThat(queriedSecond, CoreMatchers.nullValue())
    }

    @Test
    fun insertAndReadPosts() {
        // Act
        db.postDao().insertPosts(listOf(POST.copy(id = "1"), POST.copy(id = "2"), POST.copy(id = "3")))
        val queried = db.postDao().getPosts()

        // Verify
        MatcherAssert.assertThat(queried, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(queried.size, CoreMatchers.`is`(3))
    }

    @Test
    fun insertAndReadDetailedInformation() {
        // Act
        db.postDao().insertPosts(listOf(POST.copy(id = "1", user = USER.copy(links = LINKS.copy(html = "foo", likes = "bar"))),
            POST.copy(id = "2"), POST.copy(id = "3")))
        val queried = db.postDao().getPost("1")

        // Verify
        MatcherAssert.assertThat(queried, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(queried.user!!.links.html, CoreMatchers.`is`("foo"))
        MatcherAssert.assertThat(queried.user!!.links.likes, CoreMatchers.`is`("bar"))
    }

    companion object {
        // Arrange
        @JvmStatic
        val LINKS = Links(html = "", likes = "", photos = "", self = "")

        @JvmStatic
        val URLS = Urls(full = "", raw = "", regular = "", small = "", thumb = "")

        @JvmStatic
        val PROFILE_IMAGE = ProfileImage(large = "", medium = "", small = "")

        @JvmStatic
        val USER = User(id = "", links = LINKS, profileImage = PROFILE_IMAGE, name = "", username = "")

        @JvmStatic
        val POST = Post(categories = emptyList(), color = "", createdAt = "", height = 0, id = "0",
            likedByUser = false, likes = 0, links = LINKS, urls = URLS, user = USER, width = 0)
    }
}
