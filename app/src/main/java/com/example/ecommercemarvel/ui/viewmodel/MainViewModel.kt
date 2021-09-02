package com.example.ecommercemarvel.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.ecommercemarvel.data.model.Comic
import com.example.ecommercemarvel.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getComics() = liveData(Dispatchers.IO){
        emit(mainRepository.getComics())
    }

}