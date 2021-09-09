package com.example.ecommercemarvel.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.ecommercemarvel.domain.model.Comic
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
        val comic = intent.getParcelableExtra<Comic>("comic")
        if(comic != null) {
            binding.tvTitleComic.text = comic.title
            binding.tvPriceComic.text = intent.getStringExtra("quantity")?.let {
                getCheckoutPrice(comic.prices[0].price,
                    it)
            }
            binding.tvQuatityComics.text = intent.getStringExtra("quantity")
            binding.star.isVisible = comic.rare == true
            val image = comic.thumbnail.path +"."+ comic.thumbnail.extension
            Picasso.get().load(image).into(binding.ivComic)
        }
        coupon = binding.inputText
        binding.buttonBuy.setOnClickListener {
            openConfirmation()
        }
        coupon.addTextChangedListener {
            if(getConfirmationCoupon(
                    it.toString(),
                    comic!!.rare
            )){
                binding.tvDiscount.isVisible = true
                binding.tvDiscountText.isVisible = true
                binding.couponValid.isVisible = true
                binding.tvDiscount.text = getDiscount(
                    intent.getStringExtra("quantity")?.let {
                        getCheckoutPrice(comic.prices[0].price,
                            it)
                    }.toString(),
                    it.toString(),
                    comic.rare)
                binding.tvPriceComic.text = getConfimationPrice(
                    intent.getStringExtra("quantity")?.let {
                        getCheckoutPrice(comic.prices[0].price,
                            it)
                    }.toString(),
                    it.toString(),
                    comic.rare)
            } else {
                binding.tvPriceComic.text = intent.getStringExtra("quantity")?.let {
                    getCheckoutPrice(comic.prices[0].price,
                        it)
                }
                binding.couponValid.isVisible = false
                binding.tvDiscount.isVisible = false
                binding.tvDiscountText.isVisible = false
            }
        }
    }

    private fun getCheckoutPrice(price: String, quantity: String): String {
        val priceResult = price.toFloat() * quantity.toFloat()
        return String.format("%.2f", priceResult)
    }

    private fun openConfirmation() {
        val intentConfirmation = Intent(this, ConfirmationActivity::class.java)
        val comic = intent.getParcelableExtra<Comic>("comic")
        intentConfirmation.putExtra("comic",comic)
        intentConfirmation.putExtra("quantity", intent.getStringExtra("quantity"))
        intentConfirmation.putExtra("coupon", coupon.text.toString())
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