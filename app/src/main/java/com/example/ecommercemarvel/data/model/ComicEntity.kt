package com.example.ecommercemarvel.data.model

import androidx.room.Entity

@Entity(tableName = "comics")
data class ComicEntity(
    var id: Int,
    var title: String?,
    var description: String?,
    var modified: String?,
    var format: String?,
    var priceResponse: List<Price>,
    var imageResponse: GetImage?,
    var rare: Boolean?
)
