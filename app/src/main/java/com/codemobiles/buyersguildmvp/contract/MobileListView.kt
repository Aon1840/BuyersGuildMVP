package com.codemobiles.buyersguildmvp.contract

import com.codemobiles.buyersguildmvp.model.MobileResponse

interface MobileListView: BaseView {
    fun showMobileList(phoneList: ArrayList<MobileResponse>)
    fun setPreFavourite()
}