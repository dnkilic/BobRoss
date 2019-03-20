package com.dnkilic.bobross

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.dnkilic.bobross.content.OnContentLoad
import com.dnkilic.bobross.content.json.ContentFetcher
import com.dnkilic.bobross.content.model.HttpMethod
import com.dnkilic.bobross.image.ImageStyle
import com.dnkilic.bobross.image.BitmapFetcher
import java.lang.ref.WeakReference

class BobRoss {

    private var url: String? = null
    private var imageStyle: ImageStyle? = null
    private var errorPlaceHolderRes: Int? = null
    private var listener: OnContentLoad? = null

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

        /**
         * Load Json document
         */
        fun content(): BobRoss {
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
     * Load content with request method
     * @param url
     * @param listener
     */
    fun request(url: String?, listener: OnContentLoad?) {
        requireNotNull(url) {
            "Url should not be null."
        }

        this.url = url
        this.listener = listener

        val fetcher = ContentFetcher(url, HttpMethod.GET , listener)
        fetcher.fetch()
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

        val bitmapFetcher = BitmapFetcher(url, imageView, imageStyle, errorPlaceHolderRes)
        bitmapFetcher.fetch()
    }

    /**
     * Error placeholder
     * @param errorPlaceHolderRes
     */
    fun error(@DrawableRes errorPlaceHolderRes: Int?): BobRoss {
        requireNotNull(errorPlaceHolderRes) {
            "Error resource should not be null."
        }

        this.errorPlaceHolderRes = errorPlaceHolderRes
        return getBobRoss()
    }
}