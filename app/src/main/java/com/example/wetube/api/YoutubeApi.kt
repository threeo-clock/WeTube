package com.example.wetube.api

import com.example.wetube.model.HomeVideoItems
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {
    @GET("videos")
    suspend fun getHomeCategoryVideos(
        @Query("key") apiKey: String,
        @Query("regionCode") regionCode: String,
        @Query("maxResults") maxResults: Int,
        @Query("chart") chart: String = "mostPopular",
        @Query("part") part: String = "snippet, contentDetails, statistics",
    ): Response<HomeVideoItems>
    @GET("videos")
    suspend fun getHomeCategoryChannels(
        @Query("key") apiKey: String,
        @Query("regionCode") regionCode: String,
        @Query("chart") chart: String = "mostPopular",
        @Query("part") part: String = "snippet, contentDetails, statistics"
    )

}