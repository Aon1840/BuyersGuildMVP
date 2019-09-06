package com.codemobiles.presentation.presenter

import com.codemobiles.buyersguildmvp.model.PhotoListResponse
import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.model.PhotoListModel
import com.codemobiles.domain.usecase.photoList.GetPhotoUseCase
import com.codemobiles.presentation.view.DetailVIew
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


class DetailPresenter constructor(private var getPhotoUseCase: GetPhotoUseCase) : BasePresenter<DetailVIew>() {

    fun getPassData(mobile: MobileModel) {
        mView?.setName(mobile.name)
        mView?.setBrand(mobile.brand)
        mView?.setDescription(mobile.description)
        mView?.setPrice(mobile.price.toString())
        mView?.setRating(mobile.rating.toString())
    }

    fun feedImageDetail() {
        getPhotoUseCase.execute(object : DisposableObserver<List<PhotoListModel>>() {
            override fun onComplete() {

            }

            override fun onNext(photoResponse: List<PhotoListModel>) {
                val detailImage: ArrayList<PhotoListModel> = arrayListOf()
                detailImage.addAll(photoResponse)
                mView?.setImageList(detailImage)
            }

            override fun onError(e: Throwable) {

            }


        }, null)
    }
}

//apiManager.getImageList(id).subscribeOn(Schedulers.io())
//.observeOn(AndroidSchedulers.mainThread())
//.subscribe(object : Observer<List<PhotoListResponse>> {
//    override fun onComplete() {
//    }
//
//    override fun onSubscribe(d: Disposable) {
//    }
//
//    override fun onNext(photoResponse: List<PhotoListResponse>) {
//        val detailImage: ArrayList<PhotoListResponse> = arrayListOf()
//        detailImage.addAll(photoResponse)
//        mView?.setImageList(detailImage)
//    }
//
//    override fun onError(e: Throwable) {
//        e.printStackTrace()
//    }
//})