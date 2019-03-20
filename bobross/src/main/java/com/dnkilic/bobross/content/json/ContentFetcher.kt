package com.dnkilic.bobross.content.json

import com.dnkilic.bobross.base.Fetcher
import com.dnkilic.bobross.content.ContentCache
import com.dnkilic.bobross.content.OnContentLoad
import com.dnkilic.bobross.content.model.HttpMethod
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class ContentFetcher(
    private val urlStr: String?,
    private val httpMethod: HttpMethod = HttpMethod.GET,
    private val listener: OnContentLoad?
) : Fetcher {

    private fun getResult(): String? {
        val cache = ContentCache.getInstance()
        var result = cache.get(urlStr)
        if (result == null) {
            result = URL(urlStr).readText()
            if (result != null) {
                cache.addItem(urlStr!!, result)
            }
        }

        return result
    }

    override fun fetch() {
        doAsync(exceptionHandler = {
            listener?.onError(it)
        }) {
            val jsonObject = getResult()
            uiThread {
                listener?.onSuccess(jsonObject!!)
            }
        }
    }
}