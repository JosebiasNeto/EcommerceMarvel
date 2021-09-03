package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.api.ComicsAPI
import com.example.ecommercemarvel.data.api.ComicsAPI.getHTTPRequest.PUBLIC_KEY
import com.example.ecommercemarvel.data.api.ComicsAPI.getHTTPRequest.output
import com.example.ecommercemarvel.data.api.ComicsAPI.getHTTPRequest.ts
import com.example.ecommercemarvel.data.model.Comic
import com.example.ecommercemarvel.data.model.ParentAPI
import java.util.*

class ComicsAPIDatasource(private val comicsAPI: ComicsAPI) {

    suspend fun getComics() =
        comicsAPI.getComics(
            PUBLIC_KEY, ts.toString(), output)
}