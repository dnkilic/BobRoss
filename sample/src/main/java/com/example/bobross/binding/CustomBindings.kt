package com.example.bobross.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dnkilic.bobross.BobRoss
import com.dnkilic.bobross.image.ImageStyle
import com.example.bobross.R

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    BobRoss.with(imageView.context)
        .load(url)
        .error(R.drawable.ic_sync_problem)
        .asImageInto(imageView)
}

@BindingAdapter("roundedImageUrl")
fun setRoundedImageUrl(imageView: ImageView, url: String?) {
    BobRoss.with(imageView.context)
        .load(url)
        .error(R.drawable.ic_person)
        .apply(ImageStyle.ROUND)
        .configureCache(0)
        .asImageInto(imageView)
}