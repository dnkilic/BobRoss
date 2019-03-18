package com.example.bobross.repository.model

sealed class Command {
    object Loading : Command()
    class Error(val code: Int?) : Command()
    class Success(val posts: List<Post>) : Command()
}