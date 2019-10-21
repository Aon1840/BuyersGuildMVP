package com.codemobiles.data.mapper

import com.codemobiles.data.model.api.PhotoListResponse
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PhotoMapperTest {

    private var photoMapper = PhotoMapper()
    private var mPhotoApi: PhotoListResponse = mock()
    private var mPhotoArray = arrayListOf<PhotoListResponse>()


    @Before
    fun setUp() {
        mPhotoApi = PhotoListResponse(1, 1, "https://www.google.com")
        mPhotoArray.add(mPhotoApi)
    }

    @Test
    fun trasnformApiToData() {
        val data = photoMapper.trasnformApiToData(mPhotoApi)
        Assert.assertEquals(mPhotoApi.id, data.id)
        Assert.assertEquals(mPhotoApi.mobileId, data.mobileId)
        Assert.assertEquals(mPhotoApi.url, data.url)
    }

    @Test
    fun transformApiToDataList() {
        val dataArray = photoMapper.transformApiToDataList(mPhotoArray)
        Assert.assertEquals(mPhotoArray[0].id, dataArray[0].id)
        Assert.assertEquals(mPhotoArray[0].mobileId, dataArray[0].mobileId)
        Assert.assertEquals(mPhotoArray[0].url, dataArray[0].url)
    }
}