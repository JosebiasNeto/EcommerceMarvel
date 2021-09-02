package com.example.ecommercemarvel.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecommercemarvel.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

class Details : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.tvTitleComic.text = intent.getStringExtra("title")
        binding.tvDescriptionComic.text = intent.getStringExtra("description")
        binding.tvModifyComic.text = intent.getStringExtra("modified")
        binding.tvFormatComic.text = intent.getStringExtra("format")
        binding.tvPriceComic.text = intent.getStringExtra("price")
        val image = intent.getStringExtra("image")
        Picasso.get().load(image).into(binding.ivComic)


    }
}