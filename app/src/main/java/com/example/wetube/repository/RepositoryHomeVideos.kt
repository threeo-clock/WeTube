package com.example.wetube.repository

import com.example.wetube.api.RetrofitClient

class RepositoryHomeVideos {
    private val clientHome = RetrofitClient.apiService
    suspend fun getPopularVideos() = clientHome.getHomePopularVideos()
    suspend fun getCategoryVideos() = clientHome.getHomeCategoryVideos()
    suspend fun getCategoryChannels() = clientHome.getHomeCategoryChannels()
}