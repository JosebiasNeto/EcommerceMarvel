package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.api.ComicsAPI

class MainRepository(private val comicsAPI: ComicsAPI) {

    suspend fun getComics() = comicsAPI.getComics()

}