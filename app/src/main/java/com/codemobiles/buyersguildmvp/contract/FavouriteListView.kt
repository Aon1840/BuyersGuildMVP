package com.codemobiles.buyersguildmvp.contract

import com.codemobiles.buyersguildmvp.model.MobileResponse

interface FavouriteListView: BaseView {
    fun showMobileFav(mobileFav: ArrayList<MobileResponse>)
}