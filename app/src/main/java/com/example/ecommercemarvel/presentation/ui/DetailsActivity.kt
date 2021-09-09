package com.example.ecommercemarvel.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.ecommercemarvel.domain.model.Comic
import com.example.ecommercemarvel.databinding.ActivityDetailsBinding
import com.example.ecommercemarvel.domain.usecase.Rules
import com.squareup.picasso.Picasso
import me.angrybyte.numberpicker.listener.OnValueChangeListener
import me.angrybyte.numberpicker.view.ActualNumberPicker

class DetailsActivity : AppCompatActivity(), OnValueChangeListener {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var mPicker: ActualNumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        mPicker = binding.actualPicker
        val comic = intent.getParcelableExtra<Comic>("comic")
        if(comic != null) {
            binding.tvTitleComic.text = comic.title
            binding.tvDescriptionComic.text = comic.description
            binding.tvModifyComic.text = Rules.getYearFromModified(comic.modified)
            binding.tvFormatComic.text = comic.format
            binding.tvPriceComic.text = Rules.getCheckoutPriceUI(comic.prices[0].price.toFloat())
            binding.star.isVisible = comic.rare == true
            val image = (comic.thumbnail.path + "." + comic.thumbnail.extension)
            Picasso.get().load(image).into(binding.ivComic)
        }
        binding.buttonBuy.setOnClickListener {
            openCheckout()
        }
        mPicker.setListener(this)
    }

    private fun openCheckout() {
        val intentCheckout = Intent(this, CheckoutActivity::class.java)
        intentCheckout.putExtra("comic", intent.getParcelableExtra<Comic>("comic"))
        intentCheckout.putExtra("quantity", mPicker.value.toString())
        startActivity(intentCheckout)
    }
    override fun onValueChanged(oldValue: Int, newValue: Int) {
        val comic = intent.getParcelableExtra<Comic>("comic")
        if(comic != null)
        binding.tvPriceComic.text =
            Rules.getCheckoutPriceUI(comic.prices[0].price.toFloat()
                    * newValue.toFloat())
    }
}

