package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.api.database.getDatabase
import com.udacity.asteroidradar.api.repository.AsteroidRepository
import retrofit2.HttpException

class DownloadDataWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "DownloadDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = AsteroidRepository(database)

        return try {
            repository.updateAsteroids()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }

    }
}