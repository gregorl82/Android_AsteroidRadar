package com.udacity.asteroidradar.api.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.models.PictureOfDay
import com.udacity.asteroidradar.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()


interface NasaApiService {

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidsJson(
        @Query("start_date") start_date: String,
        @Query("api_key") api_key: String
    ): String


    @GET("planetary/apod")
    suspend fun getPictureOfDay(@Query("api_key") api_key: String): PictureOfDay

}

object NasaApi {
    val service = retrofit.create(NasaApiService::class.java)
}