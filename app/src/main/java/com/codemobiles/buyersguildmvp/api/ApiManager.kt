package com.codemobiles.buyersguildmvp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    private val BASE_URL = "https://scb-test-mobile.herokuapp.com/"

    private var retrofit: Retrofit? = null

    fun getPhoneList(): ApiInterface {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!.create(ApiInterface::class.java)
    }
}