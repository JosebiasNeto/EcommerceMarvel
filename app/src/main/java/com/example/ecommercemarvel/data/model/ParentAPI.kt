package com.example.ecommercemarvel.data.model

import com.google.gson.annotations.SerializedName

data class ParentAPI(
    @SerializedName("data")
    var getComicsResponse: ComicsResponse
)
