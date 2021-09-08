package com.example.ecommercemarvel.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetImage(
    var path: String,
    var extension: String,
): Parcelable
