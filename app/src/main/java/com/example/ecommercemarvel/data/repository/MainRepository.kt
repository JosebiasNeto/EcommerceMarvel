package com.example.ecommercemarvel.data.repository

import com.example.ecommercemarvel.data.model.Comic
import com.example.ecommercemarvel.data.repository.Converters.toComicEntity
import com.example.ecommercemarvel.utils.CheckNetworkConnection
import java.util.*

class MainRepository(
    private val comicsAPI: ComicsAPIDatasource,
    private val comicsDb: ComicsDBDatasource,
    private val networkConnection: CheckNetworkConnection
) {

    suspend fun getComics(): List<Comic> {

        //return setRareComics(comicsAPI.getComics().data.results)

        val isOnline: Boolean = networkConnection.checkConnection()
        if (isOnline == true) {
            if (comicsAPI.getComics().data.results
                == comicsDb.getComics()
            ) {
                return comicsDb.getComics()
            } else {
                comicsDb.delComics()
                setRareComics(comicsAPI.getComics().data.results).forEach { comic ->
                    comicsDb.insert(toComicEntity(comic))
                }
                return comicsDb.getComics()
            }
        } else {
            return comicsDb.getComics()
        }

    }

    fun setRareComics(comics: List<Comic>): List<Comic>{
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