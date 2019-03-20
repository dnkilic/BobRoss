package com.dnkilic.bobross.content

import com.dnkilic.bobross.base.BobRossCache

internal class ContentCache(size: Int): BobRossCache<String>(size) {

    companion object {
        private var instance: ContentCache? = null

        fun getInstance(): ContentCache {
            if (instance == null) {
                val maxMemory = (Runtime.getRuntime().maxMemory() / ONE_KB).toInt()
                val cacheSize = maxMemory / CACHE_RATE
                instance = ContentCache(cacheSize)
            }
            return instance as ContentCache
        }
    }
}