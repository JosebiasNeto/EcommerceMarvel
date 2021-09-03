package com.example.ecommercemarvel.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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
import com.example.ecommercemarvel.utils.OnItemClickListener
import com.example.ecommercemarvel.utils.addOnItemClickListener

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

        binding.rvMainActivity.addOnItemClickListener(object : OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {
                openComicDetails(position)
            }
        })
    }

    private fun setupObservers() {
        viewModel.getComics().observe(this, {
            refreshAdapter(it)
        })
    }
    private fun refreshAdapter(comics: List<Comic>){
        adapter.apply {
            addComics(comics)
            notifyDataSetChanged()
        }
    }
    private fun openComicDetails(idComic: Int){
        val intent = Intent(this, Details::class.java)
        val comic: Comic = adapter.getComic(idComic)
        intent.putExtra("title", comic.title)
        intent.putExtra("description", comic.description)
        intent.putExtra("modified", comic.modified)
        intent.putExtra("format", comic.format)
        intent.putExtra("price", comic.priceResponse[0].price.toString())
        intent.putExtra("image", (comic.imageResponse?.path +"."+comic.imageResponse?.extension))
        startActivity(intent)
    }

}