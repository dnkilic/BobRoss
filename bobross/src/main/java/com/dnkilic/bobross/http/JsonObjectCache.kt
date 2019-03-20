package com.dnkilic.bobross.http

import com.dnkilic.bobross.cache.BobRossCache

import org.json.JSONObject

internal class JsonObjectCache(size: Int): BobRossCache<JSONObject>(size) {

    companion object {
        private var instance: JsonObjectCache? = null

        fun getInstance(): JsonObjectCache {
            if (instance == null) {
                val maxMemory = (Runtime.getRuntime().maxMemory() / ONE_KB).toInt()
                val cacheSize = maxMemory / CACHE_RATE
                instance = JsonObjectCache(cacheSize)
            }
            return instance as JsonObjectCache
        }
    }
}