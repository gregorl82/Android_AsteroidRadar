package com.udacity.asteroidradar.api.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.models.Asteroid

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsteroids(vararg asteroids: Asteroid)

    @Query("DELETE from asteroid_data WHERE closeApproachDate < strftime('%Y-%m-%d', 'now')")
    suspend fun deleteOldAsteroids()

    @Query("SELECT * FROM asteroid_data WHERE closeApproachDate >= strftime('%Y-%m-%d', 'now') ORDER BY closeApproachDate ASC")
    fun getAsteroids(): LiveData<List<Asteroid>>

}

@Database(entities = [Asteroid::class], version = 1)
abstract class AsteroidDatabase : RoomDatabase() {

    abstract val asteroidDao: AsteroidDao

}

private lateinit var INSTANCE: AsteroidDatabase

fun getDatabase(context: Context): AsteroidDatabase {
    synchronized(AsteroidDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE =
                Room.databaseBuilder(context, AsteroidDatabase::class.java, "asteroid_database")
                    .build()
        }
        return INSTANCE
    }
}



