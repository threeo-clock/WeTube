package com.example.wetube.api

import com.example.wetube.model.HomeVideoItems
import com.example.wetube.model.SearchVideoItems
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val APIKEY = "AIzaSyCi27TW1A0853L0ALxtzt7MVJyp3rIrJh0"

interface YoutubeApi {
    @GET("videos?key=${APIKEY}")
    suspend fun getHomePopularVideos(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("videoCategoryId") categoryId: String? = null
    ) : Response<HomeVideoItems>
    @GET("videoCategories")
    suspend fun getHomeCategoryVideos(
        @Query("key") apiKey: String = APIKEY,
        @Query("regionCode") regionCode: String = "KR",
        @Query("id") id: String? = null,
        @Query("part") part: String = "snippet",
    ): Response<HomeVideoItems>
    @GET("channels")
    suspend fun getHomeCategoryChannels(
        @Query("key") apiKey: String = APIKEY,
        @Query("part") part: String = "snippet",
        @Query("id") id: String? = null
    ): Response<HomeVideoItems>
    @GET("search?key=${APIKEY}")
    suspend fun getSearchVideos(
        @Query("q") query: String?,
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int? = 10,
        @Query("regionCode") regionCode: String? = "KR",
        @Query("pageToken") pageToken: String? = null
    ) : Response<SearchVideoItems>
}