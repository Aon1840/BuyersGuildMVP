package com.codemobiles.buyersguildmvp.presenter

import com.codemobiles.buyersguildmvp.api.ApiInterface
import com.codemobiles.buyersguildmvp.contract.DetailVIew
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.codemobiles.buyersguildmvp.model.PhotoListResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class DetailPresenter constructor(var apiManager: ApiInterface) : BasePresenter<DetailVIew>() {

    fun getPassData(mobile: MobileResponse) {
        mView?.setName(mobile.name)
        mView?.setBrand(mobile.brand)
        mView?.setDescription(mobile.description)
        mView?.setPrice(mobile.price.toString())
        mView?.setRating(mobile.rating.toString())
    }

    fun feedImageDetail(id: Int) {
        apiManager.getImageList(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<PhotoListResponse>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(photoResponse: List<PhotoListResponse>) {
                    val detailImage: ArrayList<PhotoListResponse> = arrayListOf()
                    detailImage.addAll(photoResponse)
                    mView?.setImageList(detailImage)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }
}