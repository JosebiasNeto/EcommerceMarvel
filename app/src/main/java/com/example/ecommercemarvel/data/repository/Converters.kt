package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.model.*

object Converters {

    fun toComic(comicEntity: ComicEntity): Comic {
        return Comic(
        id = comicEntity.id,
        description = comicEntity.description,
        format =  comicEntity.format,
        modified = comicEntity.modified,
        title = comicEntity.title,
        rare = comicEntity.rare,
         prices = listOf(Price(comicEntity.price)),
        thumbnail = GetImage(comicEntity.path, comicEntity.extension)
        )
    }
      fun toComicEntity(comic: Comic): ComicEntity{
          return ComicEntity(
        0,
            id =  comic.id,
             title = comic.title,
        description = comic.description,
        format = comic.format,
        modified = comic.modified,
        rare = comic.rare,
        path = comic.thumbnail?.path,
        extension = comic.thumbnail?.extension,
        price = comic.prices[0].price,
          )
    }
}