package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.db.ComicsDao
import com.example.ecommercemarvel.data.model.Comic
import com.example.ecommercemarvel.data.model.ComicEntity
import com.example.ecommercemarvel.data.repository.Converters.toComicEntity

class ComicsDBDatasource(
    private val comicsDao: ComicsDao
) {
    fun insert(comic: Comic){
        val comicEntity = toComicEntity(comic)
        comicsDao.insert(comicEntity)
    }
    fun getComics(): List<Comic>{
        return comicsDao.getComics().map { comicEntity ->
            Converters.toComic(comicEntity)
        }
    }
    fun delComics(){
        comicsDao.delComics()
    }
}