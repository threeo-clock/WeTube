package com.example.wetube.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wetube.model.HomeVideoItems
import com.example.wetube.model.NewList
import com.example.wetube.repository.RepositoryHomeVideos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val repositoryHomeVideos: RepositoryHomeVideos) : ViewModel() {

    private val _popularVideosResult = MutableLiveData<List<NewList>>()
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

    private fun transformToNewList(homeVideoItems: HomeVideoItems?): List<NewList> {
        val newListItems = mutableListOf<NewList>()

        if (homeVideoItems == null || homeVideoItems.items.isNullOrEmpty()) {
            return newListItems
        }
        for (item in homeVideoItems.items) {
            val thumbnail = item.snippet.thumbnails.default.url
            val title = item.snippet.title
            val description = item.snippet.description
            val url = "https://www.youtube.com/watch?v=" + item.id

            val newList = NewList(thumbnail, title, description, url)
            newListItems.add(newList)
        }
        return newListItems
    }
}