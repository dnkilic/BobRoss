package com.dnkilic.bobross.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.dnkilic.bobross.extension.getBitmapFromUrl
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.MalformedURLException
import java.net.URL

internal class BitmapFetcher(private val urlStr: String?, private val imageView: ImageView) {

    private fun getBitmap(): Bitmap? {
        val bitmapCache = BitmapCache.getInstance()
        var bitmap = bitmapCache.get(urlStr)
        if (bitmap == null) {
            bitmap = urlStr!!.getBitmapFromUrl()
            if (bitmap != null) {
                bitmapCache.addItem(urlStr, bitmap)
            }
        }

        return bitmap
    }

    fun fetch() {
        doAsync {
            var url: URL? = null
            try {
                url = URL(urlStr)
            } catch (e: MalformedURLException) {
                requireNotNull(url) {"Invalid url : $urlStr"}
            }
            if (url != null) {
                val bitmap = getBitmap()
                uiThread {
                    imageView.setImageBitmap(bitmap)
                }
            }
        }
    }

}