package com.codemobiles.buyersguildmvp.contract

import com.codemobiles.buyersguildmvp.model.MobileResponse
import java.util.*


interface MobileListView : BaseView {
    fun showMobileList(phoneList: ArrayList<MobileResponse>)
    fun setPreFavourite()
}