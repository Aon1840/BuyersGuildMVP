package com.codemobiles.domain.model

import java.io.Serializable

data class MobileModel (
    val brand: String,
    val description: String,
    val id: Int,
    val name: String,
    val price: Double,
    val rating: Double,
    val thumbImageURL: String,
    var fav:Boolean = false
) : Serializable