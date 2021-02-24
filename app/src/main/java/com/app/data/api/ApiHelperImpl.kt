package com.app.data.api

import javax.inject.Inject

/**
 * Created by Musharib Ali on 11/2/21
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class ApiHelperImpl @Inject constructor(private val apiService: ApiService) :
    com.app.data.api.BaseDataSource() {
    //  suspend fun getCurrencyModel(param:HashMap<String,String>) = getResult { apiService.getCurrencyModel(param) }
    suspend fun getWeatherListModel() = getResult { apiService.getWeatherListModel() }
}