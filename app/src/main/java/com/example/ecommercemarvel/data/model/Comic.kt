package com.example.ecommercemarvel.data.model

import com.google.gson.annotations.SerializedName

data class Comic(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("modified")
    var modified: String?,
    @SerializedName("format")
    var format: String?,
    @SerializedName("prices")
    var priceResponse: List<Price>,
    @SerializedName("thumbnail")
    var imageResponse: GetImage?,
    var rare: Boolean?
)
