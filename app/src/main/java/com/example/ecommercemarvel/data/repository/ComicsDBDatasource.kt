package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.db.ComicsDao
import com.example.ecommercemarvel.data.model.Comic
import com.example.ecommercemarvel.data.model.ComicEntity

class ComicsDBDatasource(
    private val comicsDao: ComicsDao
) {
    suspend fun insert(comicEntity: ComicEntity){
        comicsDao.insert(comicEntity)
    }
    suspend fun getComics(): List<Comic>{
        return comicsDao.getComics().map { comicEntity ->
            Converters.toComic(comicEntity)
        }
    }
    suspend fun delComics(){
        comicsDao.delComics()
    }
}