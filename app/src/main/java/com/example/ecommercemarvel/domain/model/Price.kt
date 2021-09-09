package com.example.ecommercemarvel.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(
    var price: String
): Parcelable
