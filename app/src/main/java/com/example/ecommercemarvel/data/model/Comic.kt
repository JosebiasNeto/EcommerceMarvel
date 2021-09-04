package com.example.ecommercemarvel.data.model

data class Comic(
    var id: Int,
    var title: String?,
    var description: String?,
    var modified: String?,
    var format: String?,
    var price: String,
    var imageResponse: GetImage,
    var rare: Boolean?
)