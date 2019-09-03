package com.codemobiles.buyersguildmvp.presenter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codemobiles.buyersguildmvp.PRICE_HIGHTOLOW
import com.codemobiles.buyersguildmvp.PRICE_LOWTOHIGH
import com.codemobiles.buyersguildmvp.RATE_5_1
import com.codemobiles.buyersguildmvp.contract.FavouriteListView
import com.codemobiles.buyersguildmvp.database.AppDatabase
import com.codemobiles.buyersguildmvp.database.MobileEntity
import com.codemobiles.buyersguildmvp.model.MobileResponse

class MobileFavouriteListPresenter: BasePresenter<FavouriteListView>() {

    private var appDatabase: AppDatabase? = null

    fun initDatabase(context: Context) {
        appDatabase = AppDatabase.getInstance(context).also {appDatabase ->
            appDatabase.openHelper.readableDatabase
        }
    }

    fun removeMobileFav(mobileListFav: ArrayList<MobileResponse>, position: Int) {
        val mobile = mobileListFav[position]
        appDatabase?.favoriteDao()?.deleteFavorite(
            MobileEntity(
                mobile.id,
                mobile.name,
                mobile.description,
                mobile.brand,
                mobile.price,
                mobile.rating,
                mobile.thumbImageURL,
                mobile.fav
            )
        )
        mobileListFav.removeAt(position)
        mView?.showMobileFav(mobileListFav)
    }

    fun sortMobile(mobileList: ArrayList<MobileResponse>, sortForm: String) {
        when (sortForm) {
            PRICE_LOWTOHIGH -> {
                mobileList.sortBy { list -> list.price }
            }
            PRICE_HIGHTOLOW -> {
                mobileList.sortByDescending { list -> list.price }
            }
            RATE_5_1 -> {
                mobileList.sortByDescending { list -> list.rating }
            }
            else -> {
                mobileList.sortBy { list -> list.price }
            }
        }
        mView?.showMobileFav(mobileList)
    }

    fun setMobileFav(list: ArrayList<MobileResponse>?) {
        if (list != null) {
            mView?.showMobileFav(list)
        }
    }

    fun setImageTarget(context: Context, img: ImageView, url: String) {
        Glide.with(context)
            .load(url)
            .into(img)
    }

}