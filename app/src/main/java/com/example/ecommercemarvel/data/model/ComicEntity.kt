package com.example.ecommercemarvel.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comics")
data class ComicEntity(
    @PrimaryKey(autoGenerate = true) var idbd: Long = 0,
    var id: Int,
    var title: String,
    var description: String?,
    var modified: String,
    var format: String,
    var price: String,
    var path: String,
    var extension: String,
    var rare: Boolean
)
