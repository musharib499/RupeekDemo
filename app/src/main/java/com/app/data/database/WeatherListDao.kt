package com.app.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.data.model.WeatherResponse

/**
 * Created by Musharib Ali on 11/2/21
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
@Dao
interface WeatherListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookListModel: WeatherResponse)

    @Query("Select * from weather_list")
    fun getWeatherList(): LiveData<WeatherResponse>

}