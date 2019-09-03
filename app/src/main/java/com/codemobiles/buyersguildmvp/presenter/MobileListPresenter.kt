package com.codemobiles.buyersguildmvp.presenter

import android.util.Log
import com.codemobiles.buyersguildmvp.api.ApiManager
import com.codemobiles.buyersguildmvp.contract.MobileListView
import com.codemobiles.buyersguildmvp.model.MobileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileListPresenter: BasePresenter<MobileListView>() {

    private var mDataArray: ArrayList<MobileResponse> = arrayListOf()

    fun feedMobileList() {
        val call = ApiManager.getPhoneList().getPhones()

        call.enqueue(object : Callback<List<MobileResponse>> {
            override fun onFailure(call: Call<List<MobileResponse>>, t: Throwable) {
                Log.d("MobileListFragmentPres", "Fail")
            }

            override fun onResponse(call: Call<List<MobileResponse>>, response: Response<List<MobileResponse>>) {
                if (response.isSuccessful) {
                    mDataArray.addAll(response.body()!!)
                    mView?.showMobileList(mDataArray)
                    mView?.setPreFavourite()
                }
            }

        })
    }
}