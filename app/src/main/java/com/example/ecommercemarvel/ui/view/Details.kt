package com.example.ecommercemarvel.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import androidx.core.view.isVisible
import com.example.ecommercemarvel.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso
import me.angrybyte.numberpicker.listener.OnValueChangeListener
import me.angrybyte.numberpicker.view.ActualNumberPicker

class Details : AppCompatActivity(), OnValueChangeListener {

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
        binding.tvTitleComic.text = intent.getStringExtra("title")
        binding.tvDescriptionComic.text = intent.getStringExtra("description")
        binding.tvModifyComic.text = getYearFromModified(intent.getStringExtra("modified"))
        binding.tvFormatComic.text = intent.getStringExtra("format")
        binding.tvPriceComic.text = getCheckoutPriceUI(intent.getStringExtra("price")!!.toFloat())
        binding.star.isVisible = intent.getBooleanExtra("star", false) == true
        val image = intent.getStringExtra("image")
        Picasso.get().load(image).into(binding.ivComic)

        binding.buttonBuy.setOnClickListener {
            openCheckout()
        }
        mPicker.setListener(this)

    }

    private fun getYearFromModified(modified: String?): String {
        return modified!!.substring(0..3)
    }

    private fun getCheckoutPriceUI(price: Float): String {
        return String.format("%.2f", price)
    }

    private fun openCheckout() {

        val intentCheckout = Intent(this, Checkout::class.java)
        intentCheckout.putExtra("title", intent.getStringExtra("title"))
        intentCheckout.putExtra("price", intent.getStringExtra("price")?.let {
            getCheckoutPrice(
                it,
                mPicker.value.toString()
            )
        })
        intentCheckout.putExtra("image", intent.getStringExtra("image"))
        intentCheckout.putExtra("quantity", mPicker.value.toString())
        intentCheckout.putExtra("star", intent.getBooleanExtra("star", false))
        intentCheckout.putExtra("format", intent.getStringExtra("format"))
        intentCheckout.putExtra("modified", intent.getStringExtra("modified"))
        startActivity(intentCheckout)
    }

    private fun getCheckoutPrice(price: String, quantity: String): String {
        val result = price.toDouble() * quantity.toDouble()
        return String.format("%.2f", result)
    }

    override fun onValueChanged(oldValue: Int, newValue: Int) {
        binding.tvPriceComic.text =
            getCheckoutPriceUI((intent.getStringExtra("price")!!.toFloat()
                    * newValue.toFloat()))
    }
}

