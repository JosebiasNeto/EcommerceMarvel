package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.api.ComicsAPI
import com.example.ecommercemarvel.data.api.ComicsAPI.getHTTPRequest.PUBLIC_KEY
import com.example.ecommercemarvel.data.api.ComicsAPI.getHTTPRequest.output
import com.example.ecommercemarvel.data.api.ComicsAPI.getHTTPRequest.ts
import com.example.ecommercemarvel.data.model.Comic

class MainRepository(private val comicsAPI: ComicsAPIDatasource) {

    suspend fun getComics(): List<Comic> =
        comicsAPI.getComics()
            .getComicsResponse.comic


}