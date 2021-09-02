package com.example.ecommercemarvel.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommercemarvel.data.api.RetrofitBuilder
import com.example.ecommercemarvel.data.model.Comic
import com.example.ecommercemarvel.data.repository.ComicsAPIDatasource
import com.example.ecommercemarvel.data.repository.MainRepository
import com.example.ecommercemarvel.databinding.ActivityMainBinding
import com.example.ecommercemarvel.ui.adapter.ComicsAdapter
import com.example.ecommercemarvel.ui.viewmodel.MainViewModel
import com.example.ecommercemarvel.ui.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ComicsAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ComicsAPIDatasource(RetrofitBuilder.comicsAPI)
            )
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        binding.rvMainActivity.layoutManager = GridLayoutManager(this, 2)
        adapter = ComicsAdapter(arrayListOf())
        binding.rvMainActivity.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getComics().observe(this, {
            comic ->
                refreshAdapter(comic)
        })
    }
    private fun refreshAdapter(comics: List<Comic>){
        adapter.apply {
            addComics(comics)
            notifyDataSetChanged()
        }
    }

}