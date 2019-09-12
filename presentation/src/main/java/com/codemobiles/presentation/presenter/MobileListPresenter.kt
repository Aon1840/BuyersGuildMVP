package com.codemobiles.presentation.presenter

import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.usecase.favorite.AddFavouriteUseCase
import com.codemobiles.domain.usecase.favorite.RemoveFavouriteUseCase
import com.codemobiles.domain.usecase.mobileList.GetPhoneFavouriteListUseCase
import com.codemobiles.domain.usecase.mobileList.GetPhoneListUseCase
import com.codemobiles.presentation.PRICE_HIGHTOLOW
import com.codemobiles.presentation.PRICE_LOWTOHIGH
import com.codemobiles.presentation.RATE_5_1
import com.codemobiles.presentation.view.MobileListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Completable
import io.reactivex.observers.DisposableObserver

class MobileListPresenter constructor(
    private val getPhoneListUseCase: GetPhoneListUseCase,
    private val addFavouriteUseCase: AddFavouriteUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase,
    private val getPhoneFavouriteListUseCase: GetPhoneFavouriteListUseCase
) :
    BasePresenter<MobileListView>() {

    private var mDataArray = arrayListOf<MobileModel>()


    fun feedMobileList() {
        getPhoneListUseCase.execute(object : DisposableObserver<List<MobileModel>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<MobileModel>) {
                val mDataArray = arrayListOf<MobileModel>()
                mDataArray.addAll(t)
                mView?.setPreFavourite()
                mView?.showMobileList(mDataArray)
            }

            override fun onError(e: Throwable) {

            }

        }, null)
    }

    fun sortMobile(mobileList: ArrayList<MobileModel>, sortForm: String) {
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

    fun getCurrentFav(mobileList: ArrayList<MobileModel>, mobileFavList: ArrayList<MobileModel>?) {
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

    fun addFavoriteMobile(mobile: MobileModel) {
        addFavouriteUseCase.execute(object : DisposableObserver<Int>() {
            override fun onComplete() {}

            override fun onNext(t: Int) {
                mDataArray.add(mobile)
                mView?.favoriteAddComplete(mobile)
            }

            override fun onError(e: Throwable) {}

        }, mobile)
    }

    fun removeFavoriteMobile(mobile: MobileModel) {
        removeFavouriteUseCase.execute(object : DisposableObserver<Int>() {
            override fun onComplete() {}

            override fun onNext(t: Int) {
                mobile.fav = true
                mDataArray.remove(mobile)
            }

            override fun onError(e: Throwable) {}

        }, mobile)
    }

    fun getFavouriteMobile(): ArrayList<MobileModel> {
        return mDataArray
    }


    fun checkFavourite() {
        getPhoneFavouriteListUseCase.execute(object : DisposableObserver<List<MobileModel>>() {
            override fun onComplete() {}

            override fun onNext(t: List<MobileModel>) {
                val gson = Gson()
                val json = gson.toJson(t)
                val data = gson.fromJson<List<MobileModel>>(json, object : TypeToken<List<MobileModel>>() {}.type)
                mDataArray.addAll(data)
            }

            override fun onError(e: Throwable) {}

        }, null)
    }
}