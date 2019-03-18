package com.example.bobross.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.testOnce() = TestOnceObserver<T>().apply {
    observeForever(this)
}

class TestOnceObserver<T> : Observer<T> {

    var value: T? = null
        private set

    override fun onChanged(t: T) {
        this.value = t
    }
}
