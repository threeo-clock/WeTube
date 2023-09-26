package com.example.wetube.model

data class HomeVideoItems(
    val kind: String,
    val etag: String,
    val items: List<HomeVideoItem>?
)
data class HomeVideoItem(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: Snippet,
    val tags: List<String>,
    val contentDetails: ContentDetails,
    val statistics: Statistics
)
data class ContentDetails(
    val duration: String
)
data class Statistics(
    val viewCount: String? = ""
)