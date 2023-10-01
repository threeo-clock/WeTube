package com.example.wetube.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wetube.model.HomeVideoItems
import com.example.wetube.model.NewList
import com.example.wetube.repository.RepositoryHomeVideos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repositoryHomeVideos: RepositoryHomeVideos) : ViewModel() {

    private val _popularVideosResult = MutableLiveData<List<NewList>>()

    private val _videoCategories = MutableLiveData<List<String>?>()
    val videoCategories: LiveData<List<String>?> = _videoCategories

    val popularVideosResult: LiveData<List<NewList>>
        get() = _popularVideosResult

    fun getPopularVideosData() = viewModelScope.launch(Dispatchers.Main) {
        val response = repositoryHomeVideos.getPopularVideos()
        if (response.isSuccessful) {
            val homeVideoItems = response.body()
            val newList = transformToNewList(homeVideoItems)
            _popularVideosResult.value = newList
        } else {
            Log.d("viewmodel", "is not successful")
        }
    }

    fun getCategoryVideos() = viewModelScope.launch(Dispatchers.Main) {
        val response = repositoryHomeVideos.getCategoryVideos()
        if (response.isSuccessful) {
            val categoriesResponse = response.body()
            val categories = categoriesResponse?.items?.map { it.snippet.title }
            _videoCategories.postValue(categories)
        } else {
            Log.d("category", "통신 실패")
        }
    }


    private fun transformToNewList(homeVideoItems: HomeVideoItems?): List<NewList> {
        val newListItems = mutableListOf<NewList>()

        if (homeVideoItems == null || homeVideoItems.items.isNullOrEmpty()) {
            return newListItems
        }
        for (item in homeVideoItems.items) {
            val thumbnail = item.snippet.thumbnails.default.url
            val title = item.snippet.title
            val description = item.snippet.description

            val newList = NewList(thumbnail, title, description)
            newListItems.add(newList)
        }
        return newListItems
    }
}