package com.dnkilic.bobross

import android.content.Context
import android.widget.ImageView
import com.dnkilic.bobross.image.ImageStyle
import com.dnkilic.bobross.repository.remote.DownloadManager
import org.json.JSONObject
import java.lang.ref.WeakReference

class BobRoss {

    private var url: String? = null
    private var imageStyle: ImageStyle? = null

    companion object {
        private var context: WeakReference<Context>? = null
        private lateinit var bobRoss: WeakReference<BobRoss>

        /**
         * Construct BobRoss with weak referenced context
         * @param context
         */
        fun with(context: Context?): BobRoss {
            requireNotNull(context) {
                "Context should not be null."
            }

            this.context = WeakReference(context)
            this.bobRoss = WeakReference(BobRoss())
            return bobRoss.get()!!
        }

        private fun getBobRoss(): BobRoss {
            return bobRoss.get()!!
        }
    }

    /**
     * Load image
     * @param url
     */
    fun load(url: String?): BobRoss {
        requireNotNull(url) {
            "Url should not be null."
        }

        this.url = url
        return getBobRoss()
    }

    /**
     * Apply image style
     * Currently only supports rounded image style
     * @param imageStyle
     */
    fun apply(imageStyle: ImageStyle): BobRoss {
        this.imageStyle = imageStyle
        return getBobRoss()
    }

    /**
     * Load image into ImageView
     * @param imageView
     */
    fun asImageInto(imageView: ImageView?) {
        requireNotNull(imageView) {
            "ImageView should not be null."
        }

        val downloadManager = DownloadManager(url, imageView)
        downloadManager.downloadBitmap()
    }

    /**
     * Load Json document
     */
    fun asJson(): JSONObject {
        return JSONObject()
    }

    /**
     * Load zip file and return its path?
     */
    fun asZip() {
        // TODO implement later
    }

    /**
     * Load Xml document
     */
    fun asXml() {
        // TODO implement later
    }

    /**
     * Load Pdf and return its path?
     */
    fun asPdf() {
        // TODO implement later
    }
}