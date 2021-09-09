package com.example.ecommercemarvel.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.ecommercemarvel.domain.model.Comic
import com.example.ecommercemarvel.databinding.ActivityCheckoutBinding
import com.example.ecommercemarvel.domain.usecase.Rules
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
                Rules.getCheckoutPrice(comic.prices[0].price,
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
            if(Rules.getConfirmationCoupon(
                    it.toString(),
                    comic!!.rare
            )){
                binding.tvDiscount.isVisible = true
                binding.tvDiscountText.isVisible = true
                binding.couponValid.isVisible = true
                binding.tvDiscount.text = Rules.getDiscount(
                    intent.getStringExtra("quantity")?.let {
                        Rules.getCheckoutPrice(comic.prices[0].price,
                            it)
                    }.toString(),
                    it.toString(),
                    comic.rare)
                binding.tvPriceComic.text = Rules.getConfimationPrice(
                    intent.getStringExtra("quantity")?.let {
                        Rules.getCheckoutPrice(comic.prices[0].price,
                            it)
                    }.toString(),
                    it.toString(),
                    comic.rare)
            } else {
                binding.tvPriceComic.text = intent.getStringExtra("quantity")?.let {
                    Rules.getCheckoutPrice(comic.prices[0].price,
                        it)
                }
                binding.couponValid.isVisible = false
                binding.tvDiscount.isVisible = false
                binding.tvDiscountText.isVisible = false
            }
        }
    }

    private fun openConfirmation() {
        val intentConfirmation = Intent(this, ConfirmationActivity::class.java)
        val comic = intent.getParcelableExtra<Comic>("comic")
        intentConfirmation.putExtra("comic",comic)
        intentConfirmation.putExtra("quantity", intent.getStringExtra("quantity"))
        intentConfirmation.putExtra("coupon", coupon.text.toString())
        startActivity(intentConfirmation)
    }
}