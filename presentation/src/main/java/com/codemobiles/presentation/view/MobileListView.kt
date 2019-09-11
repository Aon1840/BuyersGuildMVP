package com.codemobiles.presentation.view

import com.codemobiles.domain.model.MobileModel
import java.util.*


interface MobileListView : BaseView {
    fun showMobileList(phoneList: ArrayList<MobileModel>)
    fun setPreFavourite()
    fun favoriteAddComplete(mobile: MobileModel)
}