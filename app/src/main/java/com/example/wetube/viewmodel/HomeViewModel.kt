package com.example.wetube.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wetube.model.ChannelItem
import com.example.wetube.model.HomeVideoItems
import com.example.wetube.model.NewList
import com.example.wetube.repository.RepositoryHomeVideos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class HomeViewModel(private val repositoryHomeVideos: RepositoryHomeVideos) : ViewModel() {

    private val _popularVideosResult = MutableLiveData<List<NewList>>()

    private val _videoCategories = MutableLiveData<List<String>?>()
    val videoCategories: LiveData<List<String>?> = _videoCategories

    private val _originalData = MutableLiveData<List<ChannelItem>>()
    val originalData: LiveData<List<ChannelItem>> get() = _originalData

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

    fun getCategoryVideos(categoryId: String) = viewModelScope.launch(Dispatchers.Main) {
        val response = repositoryHomeVideos.getCategoryVideos(categoryId)
        if (response.isSuccessful) {
            val categoriesResponse = response.body()

            val categories = categoriesResponse?.items?.map { item ->
                item.snippet.title
            }

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
            val url = "https://www.youtube.com/watch?v=" + item.id

            val newList = NewList(thumbnail, title, description, url)
            newListItems.add(newList)
        }
        return newListItems
    }

    fun fetchYoutubeData() = viewModelScope.launch(Dispatchers.Main) {
        val response = repositoryHomeVideos.getPopularVideos()
        if (response.isSuccessful) {
            val youtubeApiResponse = response.body() // 유튜브 API 응답 데이터
            if (youtubeApiResponse != null) {
                val newData = mutableListOf<ChannelItem>()
                // API 응답을 반복하면서 데이터를 newData에 추가
                for (item in youtubeApiResponse.items!!) {
                    val thumbnails = item.snippet.thumbnails
                    val thumbnailUrl = thumbnails?.default?.url ?: "기본 이미지 URL을 여기에 설정"
                    val title = item.snippet.title ?: "제목 없음"
                    val description = item.snippet.description ?: "설명 없음"
                    val channelTitle = item.snippet.channelTitle ?: "설명 없음"
                    val newList = ChannelItem(
                        thumbnail = thumbnailUrl,
                        title = title,
                        description = description,
                        channelTitle = channelTitle
                    )
                    newData.add(newList)
                }
                _originalData.value = newData
            } else {
                Log.d("RetrofitError", "API 호출 응답이 null입니다.")
            }
        } else {
            Log.d("RetrofitError", "API 호출 실패: ${response.code()} ${response.message()}")
        }
    }
}
