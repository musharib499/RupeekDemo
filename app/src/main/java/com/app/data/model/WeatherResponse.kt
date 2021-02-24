package com.app.data.model


import androidx.databinding.adapters.Converters
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_list")
data class WeatherResponse(
    @SerializedName("data")
    @TypeConverters(Converters::class)
    var `data`: List<Data?>?
) {
    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0 // 1
    data class Data(
        @SerializedName("rain")
        var rain: Int?, // 40
        @SerializedName("temp")
        var temp: Int?, // 24
        @SerializedName("time")
        var time: Long?, // 1564012800
        @SerializedName("wind")
        var wind: Int? // 15
    )
}