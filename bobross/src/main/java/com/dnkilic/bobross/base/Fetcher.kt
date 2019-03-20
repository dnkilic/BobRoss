package com.dnkilic.bobross.base

import java.net.MalformedURLException
import java.net.URL

internal interface Fetcher {
    fun fetch()
    fun validateUrl(urlStr: String?) {
        var url: URL? = null
        try {
            url = URL(urlStr)
        } catch (e: MalformedURLException) {
            requireNotNull(url) {"Invalid url : $urlStr"}
        }
    }
}