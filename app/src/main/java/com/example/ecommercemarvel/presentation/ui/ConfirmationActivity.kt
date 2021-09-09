package com.example.ecommercemarvel.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.ecommercemarvel.domain.model.Comic
import com.example.ecommercemarvel.databinding.ActivityConfirmationBinding
import com.squareup.picasso.Picasso

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        val comic = intent.getParcelableExtra<Comic>("comic")
        if(comic != null) {
            val image = comic.thumbnail.path +"."+ comic.thumbnail.extension
            Picasso.get().load(image).into(binding.ivComic)
            binding.tvTitleComic.text = comic.title
            binding.tvFormatComic.text = comic.format
            binding.tvModifyComic.text = getYearFromModified(comic.modified)
            binding.star.isVisible = comic.rare == true
            binding.tvPriceComic.text = intent.getStringExtra("quantity")?.let {
                getCheckoutPrice(comic.prices[0].price,
                    it
                )
            }?.let {
                getConfimationPrice(
                    it,
                    intent.getStringExtra("coupon")!!,
                    comic.rare)
            }

        }
        binding.tvQuantityComics.text = intent.getStringExtra("quantity")
        if (intent.getStringExtra("coupon")?.let { getConfirmationCoupon(it,comic!!.rare) } == true) {
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
    private fun getCheckoutPrice(price: String, quantity: String): String {
        val priceResult = price.toFloat() * quantity.toFloat()
        return String.format("%.2f", priceResult)
    }
    fun getConfirmationCoupon(coupon: String, star: Boolean): Boolean {
        when {
            coupon == "RARO" -> return true
            coupon == "COMUM" && !star -> return true
            else -> return false
        }
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

}