package com.codemobiles.presentation.view

import com.codemobiles.domain.model.MobileModel
import java.util.*

interface FavouriteListView : BaseView {
    fun showMobileFav(mobileFav: ArrayList<MobileModel>)
}