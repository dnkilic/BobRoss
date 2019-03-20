package com.dnkilic.bobross.base

import android.util.LruCache

internal abstract class BobRossCache<T>(maxSize: Int): LruCache<String, T>(maxSize) {

    fun addItem(key: String, value: T) {
        if (get(key) == null) {
            put(key, value)
        }
    }

    fun removeItem(key: String) {
        remove(key)
    }

    fun removeAll() {
        evictAll()
    }

    companion object {
        internal const val CACHE_RATE = 8
        internal const val ONE_KB = 1024
    }
}