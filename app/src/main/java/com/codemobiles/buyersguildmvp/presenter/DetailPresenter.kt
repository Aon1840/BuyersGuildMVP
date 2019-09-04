package com.codemobiles.buyersguildmvp.presenter

import com.codemobiles.buyersguildmvp.api.ApiInterface
import com.codemobiles.buyersguildmvp.contract.DetailVIew
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.codemobiles.buyersguildmvp.model.PhotoListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter constructor(var apiManager: ApiInterface): BasePresenter<DetailVIew>() {

    fun getPassData(mobile: MobileResponse) {
        mView?.setName(mobile.name)
        mView?.setBrand(mobile.brand)
        mView?.setDescription(mobile.description)
        mView?.setPrice(mobile.price.toString())
        mView?.setRating(mobile.rating.toString())
    }

    fun feedImageDetail(id: Int) {
        val call = apiManager.getImageList(id)
        call.enqueue(object : Callback<List<PhotoListResponse>> {
            override fun onFailure(call: Call<List<PhotoListResponse>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<PhotoListResponse>>, response: Response<List<PhotoListResponse>>) {
                if (response.isSuccessful) {
                    val detailImage: ArrayList<PhotoListResponse> = arrayListOf()
                    detailImage.addAll(response.body()!!)
                    mView?.setImageList(detailImage)
                }
            }
        })
    }
}