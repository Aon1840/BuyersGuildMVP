package com.codemobiles.data.dataRepository

import com.codemobiles.buyersguildmvp.database.MobileDAO
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.codemobiles.data.mapper.MobileEntityDataMapper
import com.codemobiles.data.model.db.MobileEntity
import com.codemobiles.data.network.ApiInterface
import com.codemobiles.domain.model.MobileModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MobileDataRepositoryTest {

    private var apiManager: ApiInterface = mock()
    private var mobileDao: MobileDAO = mock()
    private var mobileEntityDataMapper = MobileEntityDataMapper()
    private var mobileDataRapository = MobileDataRepository(apiManager, mobileEntityDataMapper, mobileDao)
    private var mobileListApi = arrayListOf<MobileResponse>()
    private var mobileListFavourite = ArrayList<MobileEntity>()
    private var mobileModel: MobileModel = mock()
    private var mobileEntity: MobileEntity = mock()

    @Before
    fun setUp() {
        mobileListApi.add(MobileResponse("test", "test", 1, "test", 3000.0, 4.5, "https://www.google.com", false))
        mobileListFavourite.add(MobileEntity(1, "test", "test", "test", 3000.0, 4.5, "https://www.google.com", true))
        mobileModel = MobileModel("test", "test", 2, "test", 3000.0, 4.5, "https://www.google.com", true)
        mobileEntity = MobileEntity(2, "test", "test", "test", 3000.0, 4.5, "https://www.google.com", true)
        Mockito.`when`(apiManager.getPhones()).thenReturn(Observable.just(mobileListApi))
        Mockito.`when`(mobileDao.queryFavorites()).thenReturn(mobileListFavourite)
    }

    @Test
    fun getPhoneList() {
        mobileDataRapository.getPhoneList()
        verify(apiManager).getPhones()
        verify(mobileDao).queryFavorites()
    }

    @Test
    fun getPhoneFavouriteList() {
        mobileDataRapository.getPhoneFavouriteList()
        verify(mobileDao).queryFavorites()
    }

    @Test
    fun addFavourite() {
        mobileDataRapository.addFavourite(mobileModel)
        verify(mobileDao).addFavorite(mobileEntity)
    }

    @Test
    fun removeFavourite() {
        mobileDataRapository.removeFavourite(mobileModel)
        verify(mobileDao).deleteFavorite(mobileEntity)
    }
}