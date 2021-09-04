package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.model.*

object Converters {

    var comicAPI: ComicAPI = ComicAPI(0,"","","","", listOf(), GetImage("",""),false)
    var comic: Comic = Comic(0,"","","","", "", GetImage("",""),false)
    var comicEntity: ComicEntity = ComicEntity(0,0,"","","","","","","",false)


    fun toComicFromEntity(comicEntity: ComicEntity): Comic {
        comicEntity.id = comic.id
        comicEntity.description = comic.description
        comicEntity.format = comic.format
        comicEntity.modified = comic.modified
        comicEntity.title = comic.title
        comicEntity.rare = comic.rare
        comicEntity.path = comic.imageResponse.path
        comicEntity.extension = comic.imageResponse.extension
        comicEntity.price = comic.price
        return comic
    }
      fun toComicEntityFromAPI(comicAPI: ComicAPI): ComicEntity{
        comicAPI.id = comicEntity.id
        comicAPI.description = comicEntity.description
        comicAPI.format = comicEntity.format
        comicAPI.modified = comicEntity.modified
        comicAPI.title = comicEntity.title
        comicAPI.rare = comicEntity.rare
        comicAPI.imageResponse?.path = comicEntity.path
        comicAPI.imageResponse?.extension = comicEntity.extension
        getCheckoutPrice(comicAPI.priceAPIResponse.price) = comicEntity.price
        return comicEntity
    }
        fun getCheckoutPrice(price: Float): String{
        return String.format("%.2f", price)
    }
}