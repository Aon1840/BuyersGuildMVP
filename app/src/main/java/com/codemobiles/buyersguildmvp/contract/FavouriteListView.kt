package com.codemobiles.buyersguildmvp.contract

import com.codemobiles.buyersguildmvp.model.MobileResponse
import java.util.*

interface FavouriteListView: BaseView {
    fun showMobileFav(mobileFav: ArrayList<MobileResponse>)
}