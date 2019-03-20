package com.dnkilic.bobross.image

import android.graphics.Bitmap
import com.dnkilic.bobross.cache.BobRossCache

internal class BitmapCache(size: Int): BobRossCache<Bitmap>(size) {

    override fun sizeOf(key: String?, value: Bitmap): Int {
        return value.byteCount / ONE_KB
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