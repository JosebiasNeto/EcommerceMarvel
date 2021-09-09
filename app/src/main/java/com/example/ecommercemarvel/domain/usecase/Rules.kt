package com.example.ecommercemarvel.domain.usecase

object Rules {
    fun getYearFromModified(modified: String?): String {
        return modified!!.substring(0..3)
    }
    fun getCheckoutPriceUI(price: Float): String {
        return String.format("%.2f", price)
    }
    fun getCheckoutPrice(price: String, quantity: String): String {
        val priceResult = price.toFloat() * quantity.toFloat()
        return String.format("%.2f", priceResult)
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
    fun getDiscount(price: String, coupon: String, star: Boolean): String {
        val newprice = price.replace(",",".")
        var discount = newprice.toDouble()
        if (getConfirmationCoupon(coupon, star)) when {
            coupon == "RARO" -> discount = (newprice.toDouble() * 0.25)
            coupon == "COMUM" -> discount = (newprice.toDouble() * 0.10)
        }
        return String.format("%.2f", discount)
    }
}