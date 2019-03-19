package com.example.bobross.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dnkilic.bobross.BobRoss
import com.dnkilic.bobross.image.ImageStyle

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    BobRoss.with(imageView.context).load(url).asImageInto(imageView)
}

@BindingAdapter("roundedImageUrl")
fun setRoundedImageUrl(imageView: ImageView, url: String?) {
    BobRoss.with(imageView.context).load(url).apply(ImageStyle.ROUND).asImageInto(imageView)
}