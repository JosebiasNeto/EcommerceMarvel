package com.example.ecommercemarvel.data.model

import com.google.gson.annotations.SerializedName

data class ComicsResponse(
    @SerializedName("results")
    var comic: List<Comic>
)
