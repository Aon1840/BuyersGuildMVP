package com.codemobiles.buyersguildmvp.presenter

import com.codemobiles.buyersguildmvp.PRICE_HIGHTOLOW
import com.codemobiles.buyersguildmvp.PRICE_LOWTOHIGH
import com.codemobiles.buyersguildmvp.RATE_5_1
import com.codemobiles.buyersguildmvp.api.ApiInterface
import com.codemobiles.buyersguildmvp.contract.MobileListView
import com.codemobiles.buyersguildmvp.database.MobileDAO
import com.codemobiles.buyersguildmvp.database.MobileEntity
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MobileListPresenter constructor(var apiManager: ApiInterface, var favouriteMobileDAO: MobileDAO) :
    BasePresenter<MobileListView>() {

    private var mDataArray: ArrayList<MobileResponse> = arrayListOf()

    fun feedMobileList() {
        apiManager.getPhones().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<MobileResponse>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(mobileResponse: List<MobileResponse>) {
                    var mDataArray: ArrayList<MobileResponse> = arrayListOf()
                    mDataArray.addAll(mobileResponse)
                    mView?.showMobileList(mDataArray)
                    mView?.setPreFavourite()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

    fun sortMobile(mobileList: ArrayList<MobileResponse>, sortForm: String) {
        when (sortForm) {
            PRICE_LOWTOHIGH -> {
                mobileList.sortBy { item -> item.price }
            }
            PRICE_HIGHTOLOW -> {
                mobileList.sortByDescending { item -> item.price }
            }
            RATE_5_1 -> {
                mobileList.sortByDescending { item -> item.rating }
            }
            else -> {
                mobileList.sortBy { item -> item.price }
            }
        }

        mView?.showMobileList(mobileList)
    }

    fun getCurrentFav(mobileList: ArrayList<MobileResponse>, mobileFavList: ArrayList<MobileResponse>?) {
        mobileList.forEach { item ->
            item.fav = false
            mobileFavList?.forEach { itemFav ->
                if (item.id == itemFav.id) {
                    item.fav = true
                }
            }
        }
        mView?.showMobileList(mobileList)
    }

    fun addFavoriteMobile(mobile: MobileResponse) {

        favouriteMobileDAO.addFavorite(
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
        mDataArray.add(mobile)
    }

    fun removeFavoriteMobile(mobile: MobileResponse) {
        favouriteMobileDAO.deleteFavorite(
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
        mobile.fav = true
        mDataArray.remove(mobile)
    }

    fun getFavouriteMobile(): ArrayList<MobileResponse> {
        return mDataArray
    }


    fun checkFavourite() {
        val result = favouriteMobileDAO.queryFavorites()
        val gson = Gson()
        val json = gson.toJson(result)
        val data = gson.fromJson<List<MobileResponse>>(json, object : TypeToken<List<MobileResponse>>() {}.type)
        mDataArray.addAll(data)
    }
}