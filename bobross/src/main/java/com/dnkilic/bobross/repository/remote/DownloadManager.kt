package com.dnkilic.bobross.repository.remote

import android.graphics.Bitmap
import android.widget.ImageView
import com.dnkilic.bobross.extension.getBitmapFromUrl
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.MalformedURLException
import java.net.URL

internal class DownloadManager(private val urlStr: String?, private val imageView: ImageView) {

    private fun getBitmap(): Bitmap? {
        var bitmap: Bitmap? = null
        if (urlStr != null) {
            bitmap = urlStr.getBitmapFromUrl()
        }
        return bitmap
    }

    fun downloadBitmap() {
        doAsync {
            var url: URL? = null
            try {
                url = URL(urlStr)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
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