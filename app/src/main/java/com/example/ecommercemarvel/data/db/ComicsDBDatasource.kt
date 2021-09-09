package com.example.ecommercemarvel.data.db

import com.example.ecommercemarvel.domain.model.Comic
import com.example.ecommercemarvel.domain.repository.Converters
import com.example.ecommercemarvel.domain.repository.Converters.toComicEntity

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