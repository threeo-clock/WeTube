package com.example.wetube.api

import com.example.wetube.model.HomeVideoItems
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val APIKEY = "AIzaSyCevsfPI5jKK5wWhwMX0QxGAzZMJ4QbS4M"

interface YoutubeApi {
    @GET("videos")
    suspend fun getHomePopularVideos(
        @Query("key") apiKey: String = APIKEY,
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular"
    ) : Call<HomeVideoItems>
    @GET("videoCategories")
    suspend fun getHomeCategoryVideos(
        @Query("key") apiKey: String = APIKEY,
        @Query("regionCode") regionCode: String = "KR",
        @Query("id") id: String,
        @Query("part") part: String = "snippet",
    ): Call<HomeVideoItems>
    @GET("channels")
    suspend fun getHomeCategoryChannels(
        @Query("key") apiKey: String = APIKEY,
        @Query("part") part: String = "snippet, contentDetails, id",
        @Query("id") id: String
    ): Call<HomeVideoItems>
}