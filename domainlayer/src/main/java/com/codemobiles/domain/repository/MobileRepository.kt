package com.codemobiles.domain.repository

import com.codemobiles.domain.model.MobileModel
import io.reactivex.Completable
import io.reactivex.Observable

interface MobileRepository {
    fun getPhoneList(): Observable<List<MobileModel>>
    fun getPhoneFavouriteList(): Observable<List<MobileModel>>
    fun addFavourite(data: MobileModel): Observable<Int>
//    fun addFavourite(data: MobileModel): Observable<Completable>
    fun removeFavourite(data: MobileModel): Observable<Int>
//    fun removeFavourite(data: MobileModel): Observable<Completable>
}