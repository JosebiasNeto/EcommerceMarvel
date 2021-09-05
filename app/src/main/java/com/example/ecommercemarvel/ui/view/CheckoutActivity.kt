package com.example.ecommercemarvel.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.ecommercemarvel.databinding.ActivityCheckoutBinding
import com.squareup.picasso.Picasso

class CheckoutActivity : AppCompatActivity() {

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
        coupon.addTextChangedListener {
            if(getConfirmationCoupon(
                    it.toString(),
                    intent.getBooleanExtra("star", false)
            )){
                binding.tvDiscount.isVisible = true
                binding.tvDiscountText.isVisible = true
                binding.couponValid.isVisible = true
                binding.tvDiscount.text = getDiscount(
                    intent.getStringExtra("price")!!,
                    it.toString(),
                    intent.getBooleanExtra("star", false))
                binding.tvPriceComic.text = getConfimationPrice(
                    intent.getStringExtra("price")!!,
                    it.toString(),
                    intent.getBooleanExtra("star", false))
            } else {
                binding.tvPriceComic.text = intent.getStringExtra("price")
                binding.couponValid.isVisible = false
                binding.tvDiscount.isVisible = false
                binding.tvDiscountText.isVisible = false
            }
        }
    }

    private fun openConfirmation() {
        val intentConfirmation = Intent(this, ConfirmationActivity::class.java)
        intentConfirmation.putExtra("image", intent.getStringExtra("image"))
        intentConfirmation.putExtra("title", intent.getStringExtra("title"))
        intentConfirmation.putExtra("format", intent.getStringExtra("format"))
        intentConfirmation.putExtra("modified", intent.getStringExtra("modified"))
        intentConfirmation.putExtra("quantity", intent.getStringExtra("quantity"))
        intentConfirmation.putExtra("price", intent.getStringExtra("price")?.let {
            getConfimationPrice(
                it, coupon.text.toString(),
                intent.getBooleanExtra("star", false)
            )
        })
        intentConfirmation.putExtra(
            "coupon", getConfirmationCoupon(
                coupon.text.toString(),
                intent.getBooleanExtra("star", false)
            )
        )
        intentConfirmation.putExtra("star", intent.getBooleanExtra("star", false))
        startActivity(intentConfirmation)
    }

    fun getConfimationPrice(price: String, coupon: String, star: Boolean): String {
        val newprice = price.replace(",",".")
        var priceResult = newprice.toDouble()
        if (getConfirmationCoupon(coupon, star)) when {
            coupon == "RARO" -> priceResult = (newprice.toDouble() * 0.75)
            coupon == "COMUM" -> priceResult = (newprice.toDouble() * 0.90)
        }
        return String.format("%.2f", priceResult)
    }

    fun getConfirmationCoupon(coupon: String, star: Boolean): Boolean {
        when {
            coupon == "RARO" -> return true
            coupon == "COMUM" && !star -> return true
            else -> return false
        }
    }
    private fun getDiscount(price: String, coupon: String, star: Boolean): String {
        val newprice = price.replace(",",".")
        var discount = newprice.toDouble()
        if (getConfirmationCoupon(coupon, star)) when {
            coupon == "RARO" -> discount = (newprice.toDouble() * 0.25)
            coupon == "COMUM" -> discount = (newprice.toDouble() * 0.10)
        }
        return String.format("%.2f", discount)
    }
}