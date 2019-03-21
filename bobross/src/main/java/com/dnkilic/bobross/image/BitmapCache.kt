package com.dnkilic.bobross.image

import android.graphics.Bitmap
import com.dnkilic.bobross.base.BobRossCache

internal class BitmapCache(size: Int): BobRossCache<Bitmap>(size) {

    override fun sizeOf(key: String?, value: Bitmap): Int {
        return value.byteCount / ONE_KB
    }

    companion object {
        private var instance: BitmapCache? = null

        fun getInstance(cacheRate: Int = CACHE_RATE): BitmapCache {
            if (instance == null) {
                val maxMemory = (Runtime.getRuntime().maxMemory() / ONE_KB).toInt()
                val cacheSize = if (cacheRate == 0) {
                   0
                } else {
                    maxMemory / cacheRate
                }

                instance = BitmapCache(cacheSize)
            }
            return instance as BitmapCache
        }
    }
}