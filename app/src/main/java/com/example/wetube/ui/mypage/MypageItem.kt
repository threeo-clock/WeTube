package com.example.wetube.ui.mypage

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
class MypageItem(
        val thumbnail: String,
        val title: String,
        var isLiked: Boolean
)
