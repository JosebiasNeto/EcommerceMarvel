package com.example.ecommercemarvel.presentation.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommercemarvel.R
import com.example.ecommercemarvel.domain.model.Comic
import com.example.ecommercemarvel.databinding.ActivityMainBinding
import com.example.ecommercemarvel.presentation.adapter.ComicsAdapter
import com.example.ecommercemarvel.presentation.viewmodel.MainViewModel
import com.example.ecommercemarvel.utils.OnItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ComicsAdapter
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObservers()
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
        val intentDetails = Intent(this, DetailsActivity::class.java)
        val comic: Comic = adapter.getComic(idComic)
        intentDetails.putExtra("comic",comic)
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
}