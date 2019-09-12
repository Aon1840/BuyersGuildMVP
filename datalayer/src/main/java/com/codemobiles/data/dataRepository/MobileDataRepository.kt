package com.codemobiles.data.dataRepository

import com.codemobiles.buyersguildmvp.database.MobileDAO
import com.codemobiles.data.mapper.MobileEntityDataMapper
import com.codemobiles.data.model.db.MobileEntity
import com.codemobiles.data.network.ApiInterface
import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.repository.MobileRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class MobileDataRepository constructor(
    private var apiManager: ApiInterface, var mobileEntityDataMapper: MobileEntityDataMapper, var mobileDao: MobileDAO
) : MobileRepository{
    override fun getPhoneList(): Observable<List<MobileModel>> {
        val api = apiManager.getPhones().map {
            mobileEntityDataMapper.transformApiToDataList(it)
        }
        val db = Observable.just(mobileDao.queryFavorites())
        return Observable.zip(api, db, BiFunction { t1, t2 ->
            val tempList = ArrayList<MobileModel>()
            for (i in 0 until t1.size) {
                var contain = false
                for (j in 0 until t2.size) {
                    if (t1[i].id == t2[j].id) {
                        contain = true
                    }
                }
                val item = t1[i]
                item.fav = contain
                tempList.add(item)
            }
            return@BiFunction tempList
        })
    }

    override fun getPhoneFavouriteList(): Observable<List<MobileModel>> {
        return Observable.just(mobileDao.queryFavorites()).map {
            mobileEntityDataMapper.transformDBToDataList(it)
        }
    }

    override fun addFavourite(data: MobileModel): Observable<Completable> {
        val item = MobileEntity(
            data.id,
            data.name,
            data.description,
            data.brand,
            data.price,
            data.rating,
            data.thumbImageURL,
            data.fav
        )
        return Observable.just(mobileDao.addFavorite(item))
    }

    override fun removeFavourite(data: MobileModel): Observable<Completable> {
        val item = MobileEntity(
            data.id,
            data.name,
            data.description,
            data.brand,
            data.price,
            data.rating,
            data.thumbImageURL,
            data.fav
        )



        return Observable.just(mobileDao.deleteFavorite(item))
    }
}