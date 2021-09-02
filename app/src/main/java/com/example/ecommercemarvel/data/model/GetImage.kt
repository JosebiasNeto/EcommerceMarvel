package com.example.ecommercemarvel.data.model

import com.google.gson.annotations.SerializedName

data class GetImage(
    @SerializedName("path")
    var path: String?,
    @SerializedName("extension")
    var extension: String?,
)
