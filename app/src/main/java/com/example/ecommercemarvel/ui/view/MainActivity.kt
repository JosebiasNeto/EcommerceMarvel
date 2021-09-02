package com.example.ecommercemarvel.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommercemarvel.databinding.ActivityMainBinding
import com.example.ecommercemarvel.ui.adapter.ComicsAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ComicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.rvMainActivity.layoutManager = GridLayoutManager(this, 2)
        adapter = ComicsAdapter(arrayListOf())
        binding.rvMainActivity.adapter = adapter
    }
}