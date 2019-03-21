package com.example.bobross

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dnkilic.bobross.content.OnContentLoad
import com.example.bobross.repository.PostRepository
import com.example.bobross.repository.model.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
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

    private var syncNotes = MutableLiveData<NoteCommand>()
    val notes : MutableLiveData<NoteCommand>
        get() = syncNotes

    fun getPosts() {
        syncPosts.value = Command.Loading
        repository.getPostsFromAPI(object : OnContentLoad {
            override fun onError(throwable: Throwable) {
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main) {
                        val localPosts = repository.getPostsFromDB()
                        if (localPosts != null && localPosts.isNotEmpty()) {
                            syncPosts.value = Command.Success(localPosts)
                        } else {
                            onError(throwable)
                        }
                    }
                }
            }

            override fun onSuccess(response: String) {
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main) {
                        val posts = generateResponse(response)
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
                    }
                }
            }
        })
    }

    private fun generateResponse(response: String): List<Post>? {
        val types = Types.newParameterizedType(List::class.java, Post::class.java)
        val adapter =  Moshi.Builder().build().adapter<List<Post>>(types)
        return adapter.fromJson(response)
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

    fun getNote() {
        syncNotes.value = NoteCommand.Loading
        repository.getNoteFromAPI(object : OnContentLoad {
            override fun onError(throwable: Throwable) {
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main) {
                        // Directly showing localized message by purpose
                        syncNotes.value = NoteCommand.Success(throwable.localizedMessage)
                    }
                }
            }

            override fun onSuccess(response: String) {
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main) {
                        val note = generateNote(response)
                        syncNotes.value = NoteCommand.Success(note)
                    }
                }
            }
        })
    }

    private fun generateNote(response: String): String {
        // XML parsing is not added by purpose
        val xml = response.split("\n")
        val heading = xml[4].trim().removePrefix("<heading>").removeSuffix("</heading>")
        val body =  xml[5].trim().removePrefix("<body>").removeSuffix("</body>")
        return "$heading: $body"
    }
}
