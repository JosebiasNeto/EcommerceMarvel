package com.example.ecommercemarvel.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.view.isVisible
import com.example.ecommercemarvel.databinding.ActivityCheckoutBinding
import com.squareup.picasso.Picasso

class Checkout : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var coupon: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.tvTitleComic.text = intent.getStringExtra("title")
        binding.tvPriceComic.text = intent.getStringExtra("price")
        binding.tvQuatityComics.text = intent.getStringExtra("quantity")
        binding.star.isVisible = intent.getBooleanExtra("star", false) == true
        val image = intent.getStringExtra("image")
        Picasso.get().load(image).into(binding.ivComic)
        coupon = binding.inputText
        binding.buttonBuy.setOnClickListener {
            openConfirmation()
        }
    }

    private fun openConfirmation() {
        val intentConfirmation = Intent(this, Confirmation::class.java)
        intentConfirmation.putExtra("image", intent.getStringExtra("image"))
        intentConfirmation.putExtra("title", intent.getStringExtra("title"))
        intentConfirmation.putExtra("format", intent.getStringExtra("format"))
        intentConfirmation.putExtra("modified", intent.getStringExtra("modified"))
        intentConfirmation.putExtra("quantity", intent.getStringExtra("quantity"))
        intentConfirmation.putExtra("price", intent.getStringExtra("price")?.let {
            getConfimationPrice(
                it,coupon.text.toString(),
                intent.getBooleanExtra("star", false))
        })
        intentConfirmation.putExtra("coupon", getConfirmationCoupon(
            coupon.text.toString(),
            intent.getBooleanExtra("star", false)))
        intentConfirmation.putExtra("star", intent.getBooleanExtra("star", false))
        startActivity(intentConfirmation)
    }
    fun getConfimationPrice(price: String, coupon: String, star: Boolean): String{
        var priceResult = price.toDouble()
        if(getConfirmationCoupon(coupon, star)) when{
            coupon == "Raro"  -> priceResult = (price.toDouble()*0.75)
            coupon == "Comum" -> priceResult = (price.toDouble()*0.90)
        }
        return String.format("%.2f", priceResult)
    }
    fun getConfirmationCoupon(coupon: String, star: Boolean) : Boolean{
        when {
            coupon == "Raro" -> return true
            coupon == "Comum" && !star -> return true
            else -> return false
        }

    }
}