package com.example.wetube.ui.home

object Data {
    val list = mutableListOf<Video>(
        Video(null,"test입니다"),
        Video(null,"test입니다"),
        Video(null,"test입니다"),
        Video(null,"test입니다"),
        Video(null,"test입니다"),
        Video(null,"test입니다"),
        Video(null,"test입니다"),
        Video(null,"test입니다"),
        Video(null,"test입니다"),
        Video(null,"test입니다"),
        Video(null,"test입니다"),
        Video(null,"test입니다"),
    )
}

data class Video(
    val image : Int?,
    val title : String
)