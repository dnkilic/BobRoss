package com.example.bobross.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("roundedImageUrl")
fun setRoundedImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(url).apply(RequestOptions.circleCropTransform()).into(imageView)
}