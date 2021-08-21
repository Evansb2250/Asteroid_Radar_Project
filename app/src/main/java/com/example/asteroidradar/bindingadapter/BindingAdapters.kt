package com.example.asteroidradar.bindingadapter


import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.asteroidradar.R
import com.example.asteroidradar.domain.Asteroid
import com.squareup.picasso.Picasso


@BindingAdapter("imgUrl")
fun bindImage(imageView: ImageView, url: String?) {
    url?.let {
        Picasso.get().load(it).into(imageView)
    }
}


@BindingAdapter("asteroidImage")
fun bindAsteroidImage(imageView: ImageView, astroid: Asteroid) {
    when (astroid.isHazardous) {
        true -> {
            imageView.setImageResource(R.drawable.asteroid_hazardous)
            imageView.contentDescription = "Astroid discovered on ${astroid.approachDate} is hazardous"
        }
        else -> {
            imageView.setImageResource(R.drawable.asteroid_safe)
            imageView.contentDescription = "Astroid discovered on ${astroid.approachDate} is non-threatening"
        }
    }


}


@BindingAdapter("convertLongToString", "type")
fun bindAndConverValue(textView: TextView, value: Double, type: Int) {
    textView.setText(
        when (type) {
            1 -> "${value} km"
            2 -> "${value} km/s"
            else -> "${value} au"
        }
    )


}