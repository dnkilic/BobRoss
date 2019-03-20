package com.dnkilic.bobross.cache

import android.util.LruCache

internal abstract class BobRossCache<T>(maxSize: Int): LruCache<String, T>(maxSize) {
    abstract fun addItem(key: String, value: T)
    abstract fun removeItem(key: String)
    abstract fun removeAll()
}