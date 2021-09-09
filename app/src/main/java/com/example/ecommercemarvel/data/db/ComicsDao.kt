package com.example.ecommercemarvel.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ecommercemarvel.domain.model.ComicEntity

@Dao
interface ComicsDao {

    @Insert
    fun insert(comicEntity: ComicEntity)

    @Query("SELECT * FROM comics")
    fun getComics(): List<ComicEntity>

    @Query("DELETE FROM comics")
    fun delComics()

}
