package com.example.ecommercemarvel.data.api

import com.example.ecommercemarvel.data.api.ComicsAPI
import com.example.ecommercemarvel.data.api.ComicsAPI.getHTTPRequest.PUBLIC_KEY
import com.example.ecommercemarvel.data.api.ComicsAPI.getHTTPRequest.output
import com.example.ecommercemarvel.data.api.ComicsAPI.getHTTPRequest.ts

class ComicsAPIDatasource(private val comicsAPI: ComicsAPI) {

    suspend fun getComics() =
        comicsAPI.getComics(
            PUBLIC_KEY, ts.toString(), output)
}