package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R

class AsteroidAdapter :
    ListAdapter<Asteroid, AsteroidAdapter.ViewHolder>(AsteroidDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.asteroid_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val codeNameText = itemView.findViewById<TextView>(R.id.code_name)
        private val closeApproachDate = itemView.findViewById<TextView>(R.id.date)
        private val imageView = itemView.findViewById<ImageView>(R.id.is_hazardous_iv)
        fun bind(asteroid: Asteroid) {
            codeNameText.text = asteroid.codename
            closeApproachDate.text = asteroid.closeApproachDate

            if (asteroid.isPotentiallyHazardous) {
                imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
            } else {
                imageView.setImageResource(R.drawable.ic_status_normal)
            }
        }
    }


    companion object AsteroidDiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }
    }
}

