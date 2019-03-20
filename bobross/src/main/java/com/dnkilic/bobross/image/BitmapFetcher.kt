package com.dnkilic.bobross.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.dnkilic.bobross.base.Fetcher
import com.dnkilic.bobross.extension.getBitmapFromUrl
import com.dnkilic.bobross.extension.getCircularBitmap
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

internal class BitmapFetcher(
    private val urlStr: String?,
    private val imageView: ImageView,
    private val imageStyle: ImageStyle?
): Fetcher {

    private fun getBitmap(): Bitmap? {
        val bitmapCache = BitmapCache.getInstance()
        var bitmap = bitmapCache.get(urlStr)
        if (bitmap == null) {
            bitmap = urlStr!!.getBitmapFromUrl()

            if (imageStyle == ImageStyle.ROUND) {
                bitmap = bitmap.getCircularBitmap()
            }

            if (bitmap != null) {
                bitmapCache.addItem(urlStr, bitmap)
            }
        }

        return bitmap
    }

    override fun fetch() {
        doAsync {
            validateUrl(urlStr)
            val bitmap = getBitmap()
            uiThread {
                imageView.setImageBitmap(bitmap)
            }
        }
    }
}