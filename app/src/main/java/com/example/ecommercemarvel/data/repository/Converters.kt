package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.model.Comic
import com.example.ecommercemarvel.data.model.ComicEntity

object Converters {

    fun toComic(comicEntity: ComicEntity): Comic {
        return Comic(
            id = comicEntity.id,
            title = comicEntity.title,
            description = comicEntity.description,
            modified = comicEntity.modified,
            format = comicEntity.format,
            priceResponse = comicEntity.priceResponse,
            imageResponse = comicEntity.imageResponse,
            rare = comicEntity.rare
        )
    }
    fun toComicEntity(comic: Comic): ComicEntity{
        return ComicEntity(
        id = comic.id,
        title = comic.title,
        description = comic.description,
        modified = comic.modified,
        format = comic.format,
        priceResponse = comic.priceResponse,
        imageResponse = comic.imageResponse,
        rare = comic.rare
        )
    }

}