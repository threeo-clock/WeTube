package com.example.wetube.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SearchVideoItems (
    var kind: String?,
    var etag: String?,
    var nextPageToken: String?,
    var regionCode: String?,
    var pageInfo: PageInfo?,
    var items: ArrayList<Item>?
)
data class PageInfo (
    var totalResults: Int,
    var resultsPerPage: Int
)
data class Item (
    var kind: String?,
    var etag: String?,
    var id: Id?,
    var snippet: Snippet?
)
data class Id (
    var kind: String?,
    var videoId: String?
)
data class Snippet (
    var publishedAt: Date?,
    var channelId: String?,
    var title: String?,
    var description: String?,
    var thumbnails: Thumbnails?,
    var channelTitle: String?,
    var liveBroadcastContent: String?,
    var publishTime: Date?
)
data class Thumbnails (
    @SerializedName("default")
    var mydefault: Default?,
    var medium: Medium?,
    var high: High?
)
data class Default (
    var url: String? = null,
    var width: Int,
    var height: Int
)
data class Medium (
    var url: String?,
    var width: Int,
    var height: Int
)
data class High (
    var url: String?,
    var width: Int,
    var height: Int
)
