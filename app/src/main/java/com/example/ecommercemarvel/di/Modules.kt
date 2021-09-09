package com.example.ecommercemarvel.di

import com.example.ecommercemarvel.data.api.ComicsAPIDatasource
import com.example.ecommercemarvel.data.api.RetrofitBuilder.comicsAPI
import com.example.ecommercemarvel.data.db.ComicsDBDatasource
import com.example.ecommercemarvel.data.db.ComicsDatabase
import com.example.ecommercemarvel.domain.repository.MainRepository
import com.example.ecommercemarvel.presentation.viewmodel.MainViewModel
import com.example.ecommercemarvel.utils.CheckNetworkConnection
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {
    val ui = module {
        viewModel {
            MainViewModel(MainRepository(
                ComicsAPIDatasource(comicsAPI),
                ComicsDBDatasource(ComicsDatabase.getDatabase(androidApplication()).comicsDao()),
                CheckNetworkConnection(androidApplication())
            ))
        }
    }
}
