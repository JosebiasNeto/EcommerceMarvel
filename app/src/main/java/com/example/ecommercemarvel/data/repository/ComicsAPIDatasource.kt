package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.api.ComicsAPI

class ComicsAPIDatasource(private val comicsAPI: ComicsAPI) {

    suspend fun getComics() = comicsAPI.getComics(
        "f3d31a9d162e766cc8317a4dcc3af5cb", ComicsAPI.getHTTPRequest.ts.toString(),
        "2fcb2e5d735889078f6508701aa3ef7c"
    )

}