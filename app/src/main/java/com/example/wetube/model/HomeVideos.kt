package com.example.wetube.model

data class HomeVideoItems(
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val prevPageToken: String,
    val pageInfo: PageInfo,
    val items: List<HomeVideoItem>?
)
data class PageInfo (
    var totalResults: Int,
    var resultsPerPage: Int
)
data class HomeVideoItem(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: Snippet,
)
data class Snippet (
    val channelId: String,
    val title: String,
    val assignable: Boolean,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val tags: List<String>,
    val categoryId: String,
    val liveBroadcastContent: String,
    val defaultLanguage: String,
    val localized : Localized,
    val defaultAudioLanguage: String
)
data class Thumbnails (
    val url: String,
    val width: String,
    val height: String
)
data class Localized(
    val title: String,
    val description: String
)