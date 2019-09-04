package com.codemobiles.buyersguildmvp.api

import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.codemobiles.buyersguildmvp.model.PhotoListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("api/mobiles/")
    fun getPhones(): Call<List<MobileResponse>>

    @GET("api/mobiles/{mobileId}/images/")
    fun getImageList(@Path("mobileId") mobileId: Int): Call<List<PhotoListResponse>>

//    companion object Factory{
//
//        private val BASE_URL="https://scb-test-mobile.herokuapp.com/"
//
//        private var retrofit:Retrofit?=null
//
//        fun getClient(): ApiInterface {
//            if(retrofit ==null){
//                retrofit =Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//            }
//            return retrofit!!.create(ApiInterface::class.java)
//        }
//
//    }
}