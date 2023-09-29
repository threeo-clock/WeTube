package com.example.wetube.repository

import com.example.wetube.api.RetrofitClient
import com.example.wetube.model.HomeVideoItems
import retrofit2.Response


// retrofit 응답 받는 애
class RepositoryHomeVideos {
    private val clientHome = RetrofitClient.apiService
    suspend fun getPopularVideos() : Response<HomeVideoItems> = clientHome.getHomePopularVideos()
    suspend fun getCategoryVideos() = clientHome.getHomeCategoryVideos()
    suspend fun getCategoryChannels() = clientHome.getHomeCategoryChannels()
}