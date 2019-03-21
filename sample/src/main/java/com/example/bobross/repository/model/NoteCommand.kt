package com.example.bobross.repository.model

sealed class NoteCommand {
    object Loading : NoteCommand()
    class Success(val result: String) : NoteCommand()
}