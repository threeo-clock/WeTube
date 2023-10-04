package com.example.wetube

import android.content.Context
import android.content.SharedPreferences
import com.example.wetube.model.NewList
import com.example.wetube.ui.mypage.MypageItem
import com.google.gson.Gson

class LikedVideoPreferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("LikedVideos", Context.MODE_PRIVATE)

    // (key, value)
    fun setVideoLiked(thumbnail: String, newList: NewList, isLiked: Boolean = true) {
        val jsonString = Gson().toJson(newList)
        preferences.edit().apply {
            putString(thumbnail, jsonString)
            putBoolean(thumbnail + "_liked", isLiked)
            apply()
        }
    }
    fun isVideoLiked(thumbnail: String): Boolean {
        return preferences.getBoolean(thumbnail + "_liked", false)
    }
    fun removeVideoLiked(thumbnail: String) {
        preferences.edit().apply {
            remove(thumbnail)
            remove(thumbnail + "_liked")
            apply()
        }
    }
    fun getLikedVideos(): List<NewList> {
        val likedVideos = mutableListOf<NewList>()
        preferences.all.filterKeys { !it.endsWith("_liked") }.forEach { (_, value) ->
            val newList: NewList = Gson().fromJson(value as String, NewList::class.java)
            likedVideos.add(newList)
        }
        return likedVideos
    }
}