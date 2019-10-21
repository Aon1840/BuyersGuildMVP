package com.codemobiles.data.mapper


import com.codemobiles.data.model.api.MobileResponse
import com.codemobiles.data.model.db.MobileEntity
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MobileEntityDataMapperTest {

    private var mobileEntityDataMapper = MobileEntityDataMapper()
    private var mDataApi: MobileResponse = mock()
    private var mDataApiArray = arrayListOf<MobileResponse>()
    private var mDatabase: MobileEntity = mock()
    private var mDatabaseArray = arrayListOf<MobileEntity>()


    @Before
    fun setUp() {
        mDataApi = MobileResponse("b1","test",1,"iphone",3000.0,4.6,"https://www.google.com",false)
        mDatabase = MobileEntity(1,"iphone","test","b1",3000.0,4.6,"https://www.google.com",false)
        mDataApiArray.add(mDataApi)
        mDatabaseArray.add(mDatabase)
    }

    @Test
    fun transformApiToDataList() {
        val dataArray = mobileEntityDataMapper.transformApiToDataList(mDataApiArray)
        Assert.assertEquals(mDataApiArray[0].id,dataArray[0].id)
        Assert.assertEquals(mDataApiArray[0].brand,dataArray[0].brand)
        Assert.assertEquals(mDataApiArray[0].description,dataArray[0].description)
        Assert.assertEquals(mDataApiArray[0].name,dataArray[0].name)
        Assert.assertEquals(mDataApiArray[0].price,dataArray[0].price,3000.0)
        Assert.assertEquals(mDataApiArray[0].rating,dataArray[0].rating,4.5)
        Assert.assertEquals(mDataApiArray[0].thumbImageURL,dataArray[0].thumbImageURL)
        Assert.assertEquals(mDataApiArray[0].fav,dataArray[0].fav)
    }

    @Test
    fun trasnformApiToData() {
        val data = mobileEntityDataMapper.trasnformApiToData(mDataApi)
        Assert.assertEquals(mDataApi.id, data.id)
        Assert.assertEquals(mDataApi.brand, data.brand)
        Assert.assertEquals(mDataApi.description, data.description)
        Assert.assertEquals(mDataApi.name, data.name)
        Assert.assertEquals(mDataApi.price, data.price,3000.0)
        Assert.assertEquals(mDataApi.rating, data.rating,4.5)
        Assert.assertEquals(mDataApi.thumbImageURL, data.thumbImageURL)
        Assert.assertEquals(mDataApi.fav, data.fav)
    }

    @Test
    fun transformDBToDataList() {
        val dataArray = mobileEntityDataMapper.transformDBToDataList(mDatabaseArray)
        Assert.assertEquals(mDataApiArray[0].id,dataArray[0].id)
        Assert.assertEquals(mDataApiArray[0].brand,dataArray[0].brand)
        Assert.assertEquals(mDataApiArray[0].description,dataArray[0].description)
        Assert.assertEquals(mDataApiArray[0].name,dataArray[0].name)
        Assert.assertEquals(mDataApiArray[0].price,dataArray[0].price,3000.0)
        Assert.assertEquals(mDataApiArray[0].rating,dataArray[0].rating,4.5)
        Assert.assertEquals(mDataApiArray[0].thumbImageURL,dataArray[0].thumbImageURL)
        Assert.assertEquals(mDataApiArray[0].fav,dataArray[0].fav)
    }

    @Test
    fun trasnformDBToData() {
        val data = mobileEntityDataMapper.trasnformDBToData(mDatabase)
        Assert.assertEquals(mDatabase.id, data.id)
        Assert.assertEquals(mDatabase.brand, data.brand)
        Assert.assertEquals(mDatabase.description, data.description)
        Assert.assertEquals(mDatabase.name, data.name)
        Assert.assertEquals(mDatabase.price, data.price,3000.0)
        Assert.assertEquals(mDatabase.rating, data.rating,4.5)
        Assert.assertEquals(mDatabase.thumbImageURL, data.thumbImageURL)
        Assert.assertEquals(mDatabase.fav, data.fav)
    }
}