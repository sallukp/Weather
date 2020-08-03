package com.weather.paris.data.database.weather

import androidx.room.*

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(entity: WeatherCacheEntity): Long

    @Query("SELECT * FROM weather WHERE dt >= :start AND dt <= :end")
    suspend fun getWeather(start: Long, end: Long): List<WeatherCacheEntity>

    @Query("DELETE FROM weather WHERE dt < :expiry")
    suspend fun deleteExpiredEntries(expiry: Long): Int
}