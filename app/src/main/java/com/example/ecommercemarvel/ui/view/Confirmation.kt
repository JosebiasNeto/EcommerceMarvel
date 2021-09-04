package com.example.ecommercemarvel.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.ecommercemarvel.databinding.ActivityConfirmationBinding
import com.squareup.picasso.Picasso

class Confirmation : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        val image = intent.getStringExtra("image")
        Picasso.get().load(image).into(binding.ivComic)
        binding.tvTitleComic.text = intent.getStringExtra("title")
        binding.tvFormatComic.text = intent.getStringExtra("format")
        binding.tvModifyComic.text = getYearFromModified(intent.getStringExtra("modified"))
        binding.tvQuantityComics.text = intent.getStringExtra("quantity")
        binding.tvPriceComic.text = intent.getStringExtra("price")
        if (intent.getBooleanExtra("coupon", false)) {
            binding.tvCheckCoupon.isVisible = true
        }
        binding.keepBuying.setOnClickListener {
            openMainActivity()
        }
    }

    private fun getYearFromModified(modified: String?): String {
        return modified!!.substring(0..3)
    }

    private fun openMainActivity() {
        val mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
    }
}