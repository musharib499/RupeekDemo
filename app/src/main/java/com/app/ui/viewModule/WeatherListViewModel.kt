package com.app.ui.viewModule

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.app.R
import com.app.data.Repository.WeatherListRepo
import com.app.data.api.LoadingState
import com.app.data.api.Resource
import com.app.data.model.WeatherResponse

class WeatherListViewModel @ViewModelInject constructor(private val repo: WeatherListRepo) : ViewModel() {
    val _loading = MutableLiveData<LoadingState>()
    val loading: LiveData<LoadingState> get() = _loading
    private val _param = MutableLiveData<HashMap<String, String>>()
    val _list: MutableLiveData<WeatherResponse> = MutableLiveData()
    val list: LiveData<WeatherResponse> get() = _list
    val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty
    fun load(lifecycleOwner: LifecycleOwner) {
        repo.getWeatherList().observe(lifecycleOwner, {
                when (it.status) {
                   Resource.Status.LOADING -> {
                        _loading.postValue(LoadingState.LOADING)
                    }
                    Resource.Status.SUCCESS -> {
                        _loading.postValue(LoadingState.LOADED)
                        with(it.data) {
                            if (this != null && data != null && data?.size ?: 0 > 0) {
                                _list.postValue(this)
                                _isEmpty.value = false
                            } else {
                                _loading.postValue(
                                    LoadingState.error(it.message))
                                _isEmpty.value = true
                            }
                        }
                    }
                    Resource.Status.ERROR -> {
                        _loading.postValue(LoadingState.error(it.message))
                    }
                }
            })
        }
    }
