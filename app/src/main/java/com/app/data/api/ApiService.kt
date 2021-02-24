package com.app.data.api

import com.app.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Musharib Ali on 11/2/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
interface ApiService {

    @GET("sde2")
    suspend fun getWeatherListModel(): Response<WeatherResponse>
}