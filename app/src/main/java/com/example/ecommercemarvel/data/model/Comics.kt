package com.example.ecommercemarvel.data.model

data class Comics(
    var id: Int,
    var title: String,
    var description: String,
    var modified: String,
    var format: String,
    var priceResponse: Price,
    var imageResponse: GetImage,
    var rare: Boolean
)
