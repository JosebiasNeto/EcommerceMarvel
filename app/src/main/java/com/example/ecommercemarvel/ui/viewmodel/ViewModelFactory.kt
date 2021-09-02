package com.example.ecommercemarvel.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommercemarvel.data.api.ComicsAPI
import com.example.ecommercemarvel.data.repository.ComicsAPIDatasource
import com.example.ecommercemarvel.data.repository.MainRepository

class ViewModelFactory(
    private val comicsAPI: ComicsAPIDatasource
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(MainRepository(comicsAPI)) as T
    }
}