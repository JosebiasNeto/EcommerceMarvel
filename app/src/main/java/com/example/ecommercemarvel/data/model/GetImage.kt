package com.example.ecommercemarvel.data.model

data class GetImage(
    var path: String,
    var extension: String,
    var image: String = path + "." + extension
)