package com.example.wetube.model

data class SearchVideoItems(
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val prevPageToken: String,
    val pageInfo: PageInfo,
    val items: List<SearchVideoItem>?
)
data class SearchVideoItem(
    val kind: String,
    val etag: String,
    val id: Id,
    val snippet: Snippet2,
)
data class Id(
    val kind: String,
    val videoId: String,
    val channelId: String,
    val playlistId: String
)
data class Snippet2(
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails2,
    val channelTitle: String
)
data class Thumbnails2(
    val default: Default,
    val high: High,
    val medium: Medium
)