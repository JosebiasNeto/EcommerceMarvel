package com.example.ecommercemarvel.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.ecommercemarvel.domain.repository.MainRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getComics() = liveData(Dispatchers.IO) {
        emit(mainRepository.getComics())
    }
    fun updateDatabase() = liveData(Dispatchers.IO) {
        emit(mainRepository.updataDatabase())
    }
}
