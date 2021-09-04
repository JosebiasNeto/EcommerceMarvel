package com.example.ecommercemarvel.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommercemarvel.data.api.ComicsAPI
import com.example.ecommercemarvel.data.repository.ComicsAPIDatasource
import com.example.ecommercemarvel.data.repository.ComicsDBDatasource
import com.example.ecommercemarvel.data.repository.MainRepository
import com.example.ecommercemarvel.utils.CheckNetworkConnection

class ViewModelFactory(
    private val comicsAPI: ComicsAPIDatasource,
    private val comicsDb: ComicsDBDatasource,
    private val networkConnection: CheckNetworkConnection
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(MainRepository(comicsAPI, comicsDb, networkConnection)) as T
    }
}