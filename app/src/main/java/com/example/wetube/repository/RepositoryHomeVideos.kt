package com.example.wetube.repository

import com.example.wetube.api.RetrofitClient
import com.example.wetube.model.HomeVideoItems
import com.example.wetube.model.SearchVideoItems
import retrofit2.Response


// retrofit 응답 받는 애
class RepositoryHomeVideos {
    private val clientHome = RetrofitClient.apiService
    suspend fun getPopularVideos() : Response<HomeVideoItems> = clientHome.getHomePopularVideos()
    suspend fun getCategoryVideos(categoryId: String) = clientHome.getHomeCategoryVideos(categoryId = categoryId)
    suspend fun getCategoryList(categoryId: Int) : Response<HomeVideoItems> = clientHome.getCategoryList(categoryId = categoryId)
    suspend fun getCategoryChannels() = clientHome.getHomeCategoryChannels()
    suspend fun getSearchVideos(searchText: String, pageToken: String? = null): Response<SearchVideoItems> = clientHome.getSearchVideos(searchText, pageToken = pageToken)
}