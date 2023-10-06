package com.example.wetube.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val baseURL = "https://www.googleapis.com/youtube/v3/"
    val apiService: YoutubeApi
        get () = instance.create(YoutubeApi::class.java)
    private val instance: Retrofit
        get() {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
        }
}