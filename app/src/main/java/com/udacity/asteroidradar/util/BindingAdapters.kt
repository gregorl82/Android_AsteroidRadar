package com.udacity.asteroidradar.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.main.AsteroidAdapter
import com.udacity.asteroidradar.main.PictureOfDayLoadingStatus
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.models.PictureOfDay

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Asteroid>?) {
    val adapter = recyclerView.adapter as AsteroidAdapter
    adapter.submitList(data)
}

@BindingAdapter("showOnlyWhenEmpty")
fun View.showOnlyWhenEmpty(asteroids: List<Asteroid>?) {
    visibility = when {
        asteroids == null || asteroids.isEmpty() -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
        imageView.contentDescription = R.string.potentially_hazardous_asteroid_image.toString()
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
        imageView.contentDescription = R.string.not_hazardous_asteroid_image.toString()
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
        imageView.contentDescription = R.string.potentially_hazardous_asteroid_image.toString()
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
        imageView.contentDescription = R.string.not_hazardous_asteroid_image.toString()
    }
}

@BindingAdapter("pictureOfDay")
fun bindPictureOfDay(imageView: ImageView, picture: PictureOfDay?) {
    if (null != picture) {
        Picasso.with(imageView.context).load(picture.url)
            .into(imageView)
        imageView.contentDescription = picture.title
    } else {
        imageView.setImageResource(R.drawable.placeholder_picture_of_day)
        imageView.contentDescription = R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet.toString()
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}
