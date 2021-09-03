package com.example.ecommercemarvel.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.ecommercemarvel.databinding.ActivityCheckoutBinding
import com.squareup.picasso.Picasso

class Checkout : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.tvTitleComic.text = intent.getStringExtra("titleC")
        binding.tvPriceComic.text = intent.getStringExtra("priceC")
        binding.tvQuatityComics.text = intent.getStringExtra("quantity")
        val image = intent.getStringExtra("imageC")
        Picasso.get().load(image).into(binding.ivComic)
        editText = binding.inputText
        binding.buttonBuy.setOnClickListener {

        }
    }
}