package com.codemobiles.presentation.view

import com.codemobiles.domain.model.PhotoListModel
import java.util.*


interface DetailVIew: BaseView {
    fun setName(name: String)
    fun setBrand(brand: String)
    fun setPrice(price: String)
    fun setDescription(description: String)
    fun setRating(rating: String)
    fun setImageList(imageList: ArrayList<PhotoListModel>)
}