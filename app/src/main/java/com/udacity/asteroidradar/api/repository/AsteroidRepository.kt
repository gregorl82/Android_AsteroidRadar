package com.udacity.asteroidradar.api.repository

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.api.database.AsteroidDatabase
import com.udacity.asteroidradar.api.network.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.models.Asteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> = database.asteroidDao.getAsteroids()

    suspend fun updateAsteroids() {
        withContext(Dispatchers.IO) {
            val asteroidsJsonString = NasaApi.service.getAsteroidsJson("2021-06-25", "ere")
            val asteroidsJson = JSONObject(asteroidsJsonString)
            val asteroids = parseAsteroidsJsonResult(asteroidsJson)
            database.asteroidDao.insertAsteroids(*asteroids.toTypedArray())
        }
    }

}