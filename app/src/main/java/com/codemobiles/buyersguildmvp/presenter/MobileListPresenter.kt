package com.codemobiles.buyersguildmvp.presenter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codemobiles.buyersguildmvp.INFORMATION
import com.codemobiles.buyersguildmvp.PRICE_HIGHTOLOW
import com.codemobiles.buyersguildmvp.PRICE_LOWTOHIGH
import com.codemobiles.buyersguildmvp.RATE_5_1
import com.codemobiles.buyersguildmvp.activity.DetailActivity
import com.codemobiles.buyersguildmvp.api.ApiManager
import com.codemobiles.buyersguildmvp.contract.MobileListView
import com.codemobiles.buyersguildmvp.database.AppDatabase
import com.codemobiles.buyersguildmvp.database.MobileEntity
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileListPresenter: BasePresenter<MobileListView>() {

    private var mDataArray: ArrayList<MobileResponse> = arrayListOf()
    private var mDatabase: AppDatabase? = null

    fun feedMobileList() {
        val call = ApiManager.getPhoneList().getPhones()

        call.enqueue(object : Callback<List<MobileResponse>> {
            override fun onFailure(call: Call<List<MobileResponse>>, t: Throwable) {
                Log.d("MobileListFragmentPres", "Fail")
            }

            override fun onResponse(call: Call<List<MobileResponse>>, response: Response<List<MobileResponse>>) {
                if (response.isSuccessful) {
                    var mDataArray: ArrayList<MobileResponse> = arrayListOf()
                    mDataArray.addAll(response.body()!!)
                    mView?.showMobileList(mDataArray)
                    mView?.setPreFavourite()
                }
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
                mobileList.sortBy {item -> item.price }
            }
        }

        mView?.showMobileList(mobileList)
    }

    fun getDetail(context: Context, mobile: MobileResponse) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(INFORMATION, mobile)
        context.startActivity(intent)
    }

    fun setImage(context: Context, target: ImageView, url: String) {
        Glide.with(context)
            .load(url)
            .into(target)
    }

    fun getCurrentFav(mobileList: ArrayList<MobileResponse>, mobileFavList: ArrayList<MobileResponse>?) {
        mobileList.forEach {item ->
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
        mDatabase?.favoriteDao()?.addFavorite(
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
        mDatabase?.favoriteDao()?.deleteFavorite(
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

    fun setupDatabase(context: Context) {
        mDatabase = AppDatabase.getInstance(context).also {
            it.openHelper.readableDatabase
        }
    }

    fun checkFavourite() {
        val result = mDatabase?.favoriteDao()?.queryFavorites()
        val gson = Gson()
        val json = gson.toJson(result)
        val data = gson.fromJson<List<MobileResponse>>(json, object : TypeToken<List<MobileResponse>>() {}.type)
        println("Add!!")
        mDataArray.addAll(data)
    }
}