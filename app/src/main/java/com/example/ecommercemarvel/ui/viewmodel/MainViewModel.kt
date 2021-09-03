package com.example.ecommercemarvel.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.ecommercemarvel.data.model.Comic
import com.example.ecommercemarvel.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import java.util.*

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getComics() = liveData(Dispatchers.IO){
        emit(setRareComics(mainRepository.getComics().getComicsResponse.comic))
    }
    fun setRareComics(comics: List<Comic>): List<Comic>{
        for (i in 0 until comics.size) {
            comics[i].rare = i in getPositionsRadom(comics.size)
        }
        return comics
    }
    fun getPositionsRadom(size: Int):List<Int> {
        var result: MutableList<Int> = arrayListOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
        for (i in 0 until size/8){
            result[i] = (1..size).radom()
        }
        val resultFinaly: List<Int> = result
        return resultFinaly
    }

    fun IntRange.radom() =
        Random().nextInt((endInclusive + 1) - start) + start
}
