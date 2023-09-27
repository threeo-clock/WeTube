package com.example.wetube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wetube.model.HomeVideoItems
import com.example.wetube.repository.RepositoryHomeVideos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repositoryHomeVideos = RepositoryHomeVideos()
    private val _popularVideosResult = MutableLiveData<HomeVideoItems>()
    val popularVideosResult: LiveData<HomeVideoItems>
        get() = _popularVideosResult

    fun getPopularVideosData() = viewModelScope.launch(Dispatchers.IO) {
        val response = repositoryHomeVideos.getPopularVideos()
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _popularVideosResult.postValue(body)
            }
        }
    }
}