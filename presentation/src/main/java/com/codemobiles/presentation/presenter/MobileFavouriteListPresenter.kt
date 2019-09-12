package com.codemobiles.presentation.presenter


import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.usecase.favorite.RemoveFavouriteUseCase
import com.codemobiles.presentation.PRICE_HIGHTOLOW
import com.codemobiles.presentation.PRICE_LOWTOHIGH
import com.codemobiles.presentation.RATE_5_1
import com.codemobiles.presentation.view.FavouriteListView
import io.reactivex.observers.DisposableObserver

class MobileFavouriteListPresenter constructor(private val removeFavouriteUseCase: RemoveFavouriteUseCase) :
    BasePresenter<FavouriteListView>() {

    fun removeMobileFav(mobileListFav: ArrayList<MobileModel>, position: Int) {
        removeFavouriteUseCase.execute(object : DisposableObserver<Int>() {
            override fun onComplete() {

            }

            override fun onNext(t: Int) {
                mobileListFav.removeAt(position)
                mView?.showMobileFav(mobileListFav)
            }

            override fun onError(e: Throwable) {

            }

        }, mobileListFav[position])
    }

    fun sortMobile(mobileList: ArrayList<MobileModel>, sortForm: String) {
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

    fun setMobileFav(list: ArrayList<MobileModel>?) {
        if (list != null) {
            mView?.showMobileFav(list)
        }
    }

}