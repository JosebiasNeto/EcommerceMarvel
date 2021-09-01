package com.example.ecommercemarvel.data.model

data class Comics(
    var id: Int,
    var title: String,
    var description: String,
    var modified: String,
    var format: String,
    var pageCount: Int,
    var priceResponse: Price,
    var imageResponse: GetImage,
)
