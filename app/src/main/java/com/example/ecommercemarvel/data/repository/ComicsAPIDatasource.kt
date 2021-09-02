package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.api.ComicsAPI

class ComicsAPIDatasource(private val comicsAPI: ComicsAPI) {

    suspend fun getComics() = comicsAPI.getComics(
        ComicsAPI.getHTTPRequest.PUBLIC_KEY, ComicsAPI.getHTTPRequest.ts.toString(),
        ComicsAPI.getHTTPRequest.output
    )

}