package com.codemobiles.domain.repository

import com.codemobiles.domain.model.MobileModel
import io.reactivex.Observable
import io.reactivex.Observer

interface MobileRepository {
    fun getPhoneList(): Observable<List<MobileModel>>
    fun addFavourite(data: MobileModel): Observer<Int>
    fun removeFavourite(data: MobileModel): Observer<Int>
}