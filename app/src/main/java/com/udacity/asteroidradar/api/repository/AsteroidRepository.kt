package com.udacity.asteroidradar.api.repository

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.api.database.AsteroidDatabase
import com.udacity.asteroidradar.api.network.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class AsteroidRepository(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> = database.asteroidDao.getAsteroids()

    suspend fun updateAsteroids() {
        withContext(Dispatchers.IO) {

            val dateFormatter =
                SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
            val startDate = dateFormatter.format(System.currentTimeMillis())

            val asteroidsResult =
                NasaApi.service.getAsteroidsJson(startDate, BuildConfig.API_KEY)
            val asteroids = parseAsteroidsJsonResult(JSONObject(asteroidsResult))

            database.asteroidDao.insertAsteroids(*asteroids.toTypedArray())
        }
    }

}