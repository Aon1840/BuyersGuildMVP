package com.codemobiles.presentation.view

import com.codemobiles.domain.model.MobileModel

interface FavouriteListView : BaseView {
    fun showMobileFav(mobileFav: ArrayList<MobileModel>)
    fun favoriteRemoveComplete(mobile: MobileModel)
}