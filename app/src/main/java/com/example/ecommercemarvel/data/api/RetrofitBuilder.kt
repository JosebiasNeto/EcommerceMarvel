package com.example.ecommercemarvel.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "http://gateway.marvel.com/v1/"

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val comicsAPI: ComicsAPI = getRetrofit().create(ComicsAPI::class.java)
}