package com.dnkilic.bobross.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

internal fun String.getBitmapFromUrl(): Bitmap? {
    var bitmap: Bitmap? = null
    try {
        val stream = URL(this).openStream()
        bitmap = BitmapFactory.decodeStream(stream)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return bitmap
}