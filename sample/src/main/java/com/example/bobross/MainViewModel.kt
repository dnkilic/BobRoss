package com.example.bobross

import androidx.lifecycle.ViewModel
import com.example.bobross.repository.PostRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private var repository: PostRepository) : ViewModel() {
    // TODO: Implement the ViewModel
}
