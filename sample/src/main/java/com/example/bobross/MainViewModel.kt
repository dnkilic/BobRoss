package com.example.bobross

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bobross.repository.PostRepository
import com.example.bobross.repository.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class MainViewModel @Inject constructor(private var repository: PostRepository) : ViewModel() {

    private var syncPosts = MutableLiveData<Command>()

    val posts : MutableLiveData<Command>
        get() = syncPosts

    fun getPosts() {
        syncPosts.value = Command.Loading
        CoroutineScope(Dispatchers.IO).launch {
            val request = repository.getPostsFromAPI()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        val posts = response.body()
                        if (posts != null && posts.isNotEmpty()) {
                            syncPosts.value = Command.Success(posts)
                            repository.savePosts(posts)
                        } else {
                            val localPosts = repository.getPostsFromDB()
                            if (localPosts != null && localPosts.isNotEmpty()) {
                                syncPosts.value = Command.Success(localPosts)
                            } else {
                                onError(ExampleException(ERROR_POST_ARE_NOT_AVAILABLE))
                            }
                        }
                    } else {
                        val localPosts = repository.getPostsFromDB()
                        if (localPosts != null && localPosts.isNotEmpty()) {
                            syncPosts.value = Command.Success(localPosts)
                        } else {
                            onError(ExampleException(response.code()))
                        }
                    }
                } catch (exception: Exception) {
                    onError(exception)
                } catch (throwable: Throwable) {
                    onError(throwable)
                }
            }
        }
    }

    @VisibleForTesting
    fun onError(exception: Throwable) {
        syncPosts.value = when (exception) {
            is ExampleException -> Command.Error(ERROR_NETWORK)
            is UnknownHostException -> Command.Error(ERROR_NETWORK)
            is IOException -> Command.Error(ERROR_NETWORK)
            else -> Command.Error(ERROR_UNKNOWN)
        }
    }
}
