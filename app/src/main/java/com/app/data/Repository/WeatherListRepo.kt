package com.app.data.Repository

import com.app.data.api.ApiHelperImpl
import com.app.data.api.loadData
import com.app.data.database.WeatherListDao
import javax.inject.Inject

/**
 * Created by Musharib Ali on 11/2/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class WeatherListRepo @Inject constructor(private val api: ApiHelperImpl, private val local: WeatherListDao) {
    fun getWeatherList() = loadData(databaseQuery = { local.getWeatherList() }, networkCall = { api.getWeatherListModel() }, saveCallResult = { local.insert(it) })
}