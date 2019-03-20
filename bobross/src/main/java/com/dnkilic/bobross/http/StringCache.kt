package com.dnkilic.bobross.http

import com.dnkilic.bobross.cache.BobRossCache

internal class StringCache(size: Int): BobRossCache<String>(size) {

    companion object {
        private var instance: StringCache? = null

        fun getInstance(): StringCache {
            if (instance == null) {
                val maxMemory = (Runtime.getRuntime().maxMemory() / ONE_KB).toInt()
                val cacheSize = maxMemory / CACHE_RATE
                instance = StringCache(cacheSize)
            }
            return instance as StringCache
        }
    }
}