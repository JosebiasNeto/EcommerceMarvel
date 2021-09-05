package com.example.ecommercemarvel.ui.view

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import androidx.core.graphics.toColor
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommercemarvel.R
import com.example.ecommercemarvel.data.api.RetrofitBuilder
import com.example.ecommercemarvel.data.db.ComicsDatabase
import com.example.ecommercemarvel.data.model.Comic
import com.example.ecommercemarvel.data.repository.ComicsAPIDatasource
import com.example.ecommercemarvel.data.repository.ComicsDBDatasource
import com.example.ecommercemarvel.databinding.ActivityMainBinding
import com.example.ecommercemarvel.ui.adapter.ComicsAdapter
import com.example.ecommercemarvel.ui.viewmodel.MainViewModel
import com.example.ecommercemarvel.ui.viewmodel.ViewModelFactory
import com.example.ecommercemarvel.utils.CheckNetworkConnection
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
                ComicsAPIDatasource(RetrofitBuilder.comicsAPI),
                ComicsDBDatasource(ComicsDatabase.getDatabase(this).comicsDao()),
                CheckNetworkConnection(this)
            )
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        binding.rvMainActivity.layoutManager = GridLayoutManager(this, 2)
        adapter = ComicsAdapter(arrayListOf())
        binding.rvMainActivity.adapter = adapter

        binding.rvMainActivity.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                openComicDetails(position)
            }
        })
    }

    private fun setupObservers() {
        viewModel.getComics().observe(this, {
            refreshAdapter(it)
            viewModel.updateDatabase().observe(this,{
                it
            })
        })
    }

    private fun refreshAdapter(comics: List<Comic>) {
        adapter.apply {
            addComics(comics)
            notifyDataSetChanged()
        }
    }

    private fun openComicDetails(idComic: Int) {
        val intentDetails = Intent(this, Details::class.java)
        val comic: Comic = adapter.getComic(idComic)
        intentDetails.putExtra("title", comic.title)
        intentDetails.putExtra("description", comic.description)
        intentDetails.putExtra("modified", comic.modified)
        intentDetails.putExtra("format", comic.format)
        intentDetails.putExtra("price", getCheckoutPriceUI(comic.prices[0].price))
        intentDetails.putExtra("image", (comic.thumbnail?.path + "." + comic.thumbnail?.extension))
        intentDetails.putExtra("star", comic.rare)
        startActivity(intentDetails)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)

        val searchManager: SearchManager = getSystemService(
            Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search_button)
        val searchView = searchItem!!.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }
            override fun onQueryTextChange(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }
        })
        searchView.setSearchableInfo(searchManager
            .getSearchableInfo(componentName))
        searchView.maxWidth
        return true
    }
    private fun getCheckoutPriceUI(price: String): String {
        val newprice = price.toFloat()
        return String.format("%.2f", newprice)
    }
}