package com.codemobiles.presentation.presenter

import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.model.PhotoListModel
import com.codemobiles.domain.usecase.photoList.GetPhotoUseCase
import com.codemobiles.presentation.view.DetailVIew
import io.reactivex.observers.DisposableObserver


class DetailPresenter constructor(private var getPhotoUseCase: GetPhotoUseCase) : BasePresenter<DetailVIew>()  {

    fun getPassData(mobile: MobileModel?) {
        mView?.setName(mobile?.name)
        mView?.setBrand(mobile?.brand)
        mView?.setDescription(mobile?.description)
        mView?.setPrice(mobile?.price.toString())
        mView?.setRating(mobile?.rating.toString())
    }

    fun feedImageDetail(id: Int?) {
        getPhotoUseCase.execute(object : DisposableObserver<List<PhotoListModel>>() {
            override fun onComplete() {

            }

            override fun onNext(photoResponse: List<PhotoListModel>) {
                val detailImage: ArrayList<PhotoListModel>? = arrayListOf()
                detailImage?.addAll(photoResponse)
                mView?.setImageList(detailImage)
            }

            override fun onError(e: Throwable) {

            }


        }, id)
    }
}
