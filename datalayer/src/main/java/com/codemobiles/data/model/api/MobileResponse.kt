package com.codemobiles.buyersguildmvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileResponse(
    val brand: String,
    val description: String,
    val id: Int,
    val name: String,
    val price: Double,
    val rating: Double,
    val thumbImageURL: String,
    var fav: Boolean = false
) : Parcelable