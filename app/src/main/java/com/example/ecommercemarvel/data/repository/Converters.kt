package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.model.*

object Converters {

    var comic: Comic = Comic(0,"","","","", listOf(Price("")), GetImage("",""),false)
    var comicEntity: ComicEntity = ComicEntity(0,0,"","","","", "","","",false)


    fun toComic(comicEntity: ComicEntity): Comic {
        comicEntity.id = comic.id
        comicEntity.description = comic.description
        comicEntity.format = comic.format
        comicEntity.modified = comic.modified
        comicEntity.title = comic.title
        comicEntity.rare = comic.rare
        comicEntity.path = comic.thumbnail?.path ?: ""
        comicEntity.extension = comic.thumbnail?.extension ?: ""
        comicEntity.price = comic.prices[0].price
        return comic
    }
      fun toComicEntity(comic: Comic): ComicEntity{
        comic.id = comicEntity.id
        comic.description = comicEntity.description
        comic.format = comicEntity.format
        comic.modified = comicEntity.modified
        comic.title = comicEntity.title
        comic.rare = comicEntity.rare
        comic.thumbnail?.path = comicEntity.path
        comic.thumbnail?.extension = comicEntity.extension
        comic.prices[0].price = comicEntity.price
        return comicEntity
    }
}