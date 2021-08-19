package com.example.asteroidradar.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("imgUrl")
fun bindImage(imageView: ImageView, url:String?){
    url?.let {
        Picasso.get().load(it).into(imageView)
    }
}