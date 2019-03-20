package com.dnkilic.bobross.image

import android.graphics.Bitmap
import com.dnkilic.bobross.cache.BobRossCache

private const val CACHE_RATE = 8
private const val ONE_KB = 1024

internal class BitmapCache(size: Int): BobRossCache<Bitmap>(size) {

    override fun sizeOf(key: String?, value: Bitmap): Int {
        return value.byteCount / ONE_KB
    }

    override fun addItem(key: String, value: Bitmap) {
        if (get(key) == null) {
            put(key, value)
        }
    }

    override fun removeItem(key: String) {
        remove(key)
    }

    override fun removeAll() {
        evictAll()
    }

    companion object {
        private var instance: BitmapCache? = null

        fun getInstance(): BitmapCache {
            if (instance == null) {
                val maxMemory = (Runtime.getRuntime().maxMemory() / ONE_KB).toInt()
                val cacheSize = maxMemory / CACHE_RATE
                instance = BitmapCache(cacheSize)
            }
            return instance as BitmapCache
        }
    }
}