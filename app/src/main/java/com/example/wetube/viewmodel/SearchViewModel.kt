package com.example.wetube.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wetube.model.HomeVideoItems
import com.example.wetube.model.NewList
import com.example.wetube.model.SearchVideoItems
import com.example.wetube.repository.RepositoryHomeVideos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val repositoryHomeVideos: RepositoryHomeVideos) : ViewModel() {
    private val _searchVideosResult = MutableLiveData<List<NewList>>()
    val searchVideosResult: LiveData<List<NewList>>
        get() = _searchVideosResult
    var nextPageToken: String? = null
    var isLoaging = false
    private val existingData = mutableListOf<NewList>()
    var totalResults: Int = 0
    var receivedResults: Int = 0

    fun getSearchVideosData(searchText: String, context: Context) =
        viewModelScope.launch(Dispatchers.Main) {
            if (isLoaging) return@launch
            try {
                val response =
                    repositoryHomeVideos.getSearchVideos(searchText, pageToken = nextPageToken)
                if (response.isSuccessful) {
                    val searchVideoItems = response.body()
                    val newList = transformToNewList(searchVideoItems)
                    val filteredList = newList.filter { !existingData.contains(it) }
                    existingData.clear()
                    existingData.addAll(filteredList)
                    _searchVideosResult.value = existingData
                    nextPageToken = searchVideoItems?.nextPageToken
                    totalResults = searchVideoItems?.pageInfo?.totalResults ?: 0
                    receivedResults += newList.size
                } else {
                    when (response.code()) {
                        429 -> {
                            Toast.makeText(
                                context,
                                "API 호출이 제한되었습니다. 잠시후에 다시 시도해주세요.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            Log.d("Search ViewModel", "HTTP Error: ${response.code()}")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Search viewModel", "Error: ${e.message}")
            }
        }
}

private fun transformToNewList(searchVideoItems: SearchVideoItems?): List<NewList> {
    val newListItems = mutableListOf<NewList>()

    if (searchVideoItems == null || searchVideoItems.items.isNullOrEmpty()) {
        return newListItems
    }
    for (item in searchVideoItems.items) {
        val thumbnail = item.snippet.thumbnails.default.url
        val title = item.snippet.title
        val description = item.snippet.description
        val url = item.snippet.thumbnails.default.url

        val newList = NewList(thumbnail, title, description, url)
        newListItems.add(newList)
    }
    return newListItems
}
