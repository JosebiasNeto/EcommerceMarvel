package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.model.Comic
import com.example.ecommercemarvel.data.model.ComicAPI
import com.example.ecommercemarvel.data.repository.Converters.toComicFromEntity
import com.example.ecommercemarvel.data.repository.Converters.toComicEntityFromAPI
import com.example.ecommercemarvel.utils.CheckNetworkConnection
import java.util.*

class MainRepository(
    private val comicsAPI: ComicsAPIDatasource,
    private val comicsDb: ComicsDBDatasource,
    private val networkConnection: CheckNetworkConnection
) {

    suspend fun getComics(): List<Comic> {
        val isOnline: Boolean = networkConnection.checkConnection()
        if (isOnline == true) {
            if (comicsAPI.getComics().getComicsResponse.comicAPI
                == comicsDb.getComics()
            ) {
                return comicsDb.getComics()
            } else {
                comicsDb.delComics()
                setRareComics(comicsAPI.getComics().getComicsResponse.comicAPI).forEach { comicAPI ->
                    comicsDb.insert(toComicEntityFromAPI(comicAPI))
                }
                return comicsDb.getComics()
            }
        } else {
            return comicsDb.getComics()
        }
    }

    fun setRareComics(comics: List<ComicAPI>): List<ComicAPI>{
        comics.forEach {
            it.rare = false
        }
        for (i in 0 until comics.size) {
            comics[i].rare = i in getPositionsRadom(comics.size)
        }
        return comics
    }
    fun getPositionsRadom(size: Int):List<Int> {
        val result: MutableList<Int> = mutableListOf()
        while (result.size <= size*12/100){
            val a: Int = (0..size).radom()
            if (result.contains(a)){
            } else {
                result.add(a)
            }
        }
        result.removeLast()
        val resultFinaly: List<Int> = result
        return resultFinaly
    }

    fun IntRange.radom() =
        Random().nextInt((endInclusive + 1) - start) + start
}