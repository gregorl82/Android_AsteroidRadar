package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.api.database.AsteroidDatabase
import com.udacity.asteroidradar.api.network.NasaApi
import com.udacity.asteroidradar.api.network.parseAsteroidsJsonResult
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class AsteroidRepository(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> = database.asteroidDao.getAsteroids()

    suspend fun fetchAsteroids() {
        withContext(Dispatchers.IO) {

            val dateFormatter =
                SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
            val startDate = dateFormatter.format(System.currentTimeMillis())

            try {
                val asteroidsResult =
                    NasaApi.service.getAsteroidsJson(startDate, BuildConfig.API_KEY)
                val asteroids = parseAsteroidsJsonResult(JSONObject(asteroidsResult))
                database.asteroidDao.insertAsteroids(*asteroids.toTypedArray())
            } catch (e: HttpException) {
                Timber.e(e.message())
            }

        }
    }

    suspend fun deleteAsteroids() {
        withContext(Dispatchers.IO) {
            database.asteroidDao.deleteOldAsteroids()
        }
    }

    suspend fun loadPictureOfDay() = withContext(Dispatchers.IO) {
        val picture = NasaApi.service.getPictureOfDay(BuildConfig.API_KEY)

        try {
            if (picture.mediaType == "image") {
                return@withContext picture
            } else {
                return@withContext null
            }
        } catch (e: HttpException) {
            Timber.e(e.message())
            return@withContext null
        }


    }

}