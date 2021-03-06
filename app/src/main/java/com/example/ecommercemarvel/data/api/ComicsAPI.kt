package com.example.ecommercemarvel.data.api

import com.example.ecommercemarvel.domain.model.ParentAPI
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest

interface ComicsAPI {

    @GET("v1/public/comics")
    suspend fun getComics(
        @Query("apikey") apikey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): ParentAPI

    object getHTTPRequest {
        const val PUBLIC_KEY = "f3d31a9d162e766cc8317a4dcc3af5cb"
        const val PRIVATE_KEY = "d9062e679217e8957884d76b9f8360580edb5126"
        val ts: Long = System.currentTimeMillis()
        val input: String = ts.toString() + PRIVATE_KEY + PUBLIC_KEY

        fun md5Hahs(input: String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        val output: String = md5Hahs(input)
    }

}

