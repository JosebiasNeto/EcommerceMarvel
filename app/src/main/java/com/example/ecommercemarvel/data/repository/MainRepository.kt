package com.example.ecommercemarvel.data.repository

class MainRepository(private val comicsAPI: ComicsAPIDatasource) {
    suspend fun getComics() =
        comicsAPI.getComics()
}