package com.example.wetube

import android.content.Context
import android.content.SharedPreferences
import com.example.wetube.model.NewList
import com.example.wetube.ui.mypage.MypageItem
import com.google.gson.Gson

class LikedVideoPreferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("LikedVideos", Context.MODE_PRIVATE)
    fun setVideoLiked(thumbnail: String, newList: NewList) {
        val jsonString = Gson().toJson(newList)
        preferences.edit().putString(thumbnail, jsonString).apply()
    }
    fun isVideoLiked(thumbnail: String): Boolean {
        return preferences.contains(thumbnail)
    }
    fun removeVideoLiked(thumbnail: String) {
        return preferences.edit().remove(thumbnail).apply()
    }
    fun getLikedVideos(): List<NewList> {
        val likedVideos = mutableListOf<NewList>()
        preferences.all.forEach {(_, value) ->
            val newList: NewList = Gson().fromJson(value as String, NewList::class.java)
            likedVideos.add(newList)
        }
        return likedVideos
    }
}