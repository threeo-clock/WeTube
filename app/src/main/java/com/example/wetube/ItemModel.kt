package com.example.wetube

import android.icu.text.CaseMap.Title
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemModel(
        var title: String,
        var url: String,
        var channelName: String,
        var channelInfo: String,
) : Parcelable