package com.example.wetube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.wetube.ui.mypage.MypageItem

class LikesViewModel : ViewModel() {

    private val _likedVideosLiveData = MutableLiveData<MutableSet<MypageItem>>()
    val likedVideosLiveData : LiveData<List<MypageItem>> get() = _likedVideosLiveData.map { it.toList() }

    fun toggleLike(mypageItem : MypageItem) {

        val currentSet = _likedVideosLiveData.value ?: mutableSetOf()
        val removeItem = currentSet.find { it.thumbnail == mypageItem.thumbnail }
        if (removeItem != null) {
            currentSet.remove(removeItem)
        } else {
            currentSet.add(mypageItem)
        }
        _likedVideosLiveData.value = currentSet
    }
}