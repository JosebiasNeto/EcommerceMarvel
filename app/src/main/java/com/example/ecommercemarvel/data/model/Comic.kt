package com.example.ecommercemarvel.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comic(
    var id: Int,
    var title: String,
    var description: String?,
    var modified: String,
    var format: String,
    var prices: List<Price>,
    var thumbnail: GetImage,
    var rare: Boolean
): Parcelable
