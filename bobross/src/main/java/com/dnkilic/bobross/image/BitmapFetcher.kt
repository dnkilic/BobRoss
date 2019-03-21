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
    private val imageStyle: ImageStyle?,
    private val errorPlaceHolderRes: Int?,
    private val cacheRate: Int?
): Fetcher {

    private fun getBitmap(): Bitmap? {
        val bitmapCache = if (cacheRate != null) {
            BitmapCache.getInstance(cacheRate)
        } else {
            BitmapCache.getInstance()
        }

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
        doAsync(exceptionHandler = { throwable ->
            throwable.printStackTrace()
            errorPlaceHolderRes?.let { imageView.setImageResource(it) }
        }) {
            validateUrl(urlStr)
            val bitmap = getBitmap()
            uiThread {
                with(imageView) {
                    if (bitmap != null) {
                        setImageBitmap(bitmap)
                    } else {
                        errorPlaceHolderRes?.let { setImageResource(it) }
                    }
                }
            }
        }
    }
}