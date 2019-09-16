package com.codemobiles.presentation.view

import com.codemobiles.domain.model.MobileModel

interface MobileListView : BaseView {
    fun showMobileList(phoneList: ArrayList<MobileModel>)
    fun setPreFavourite()
    fun favoriteAddComplete(mobile: MobileModel)
    fun favoriteRemoveComplete(mobile: MobileModel)
}