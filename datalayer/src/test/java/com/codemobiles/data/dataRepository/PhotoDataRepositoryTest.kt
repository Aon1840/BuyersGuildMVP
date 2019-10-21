package com.codemobiles.data.dataRepository

import com.codemobiles.data.model.api.PhotoListResponse
import com.codemobiles.data.mapper.PhotoMapper
import com.codemobiles.data.network.ApiInterface
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class PhotoDataRepositoryTest {

    private var apiManager: ApiInterface = mock()
    private var photoMapper: PhotoMapper = mock()
    private var photoDataRepository = PhotoDataRepository(apiManager, photoMapper)
    private var photoListApi = arrayListOf<PhotoListResponse>()

    @Before
    fun setUp() {
        photoListApi.add(PhotoListResponse(1, 1, "https://www.google.com"))
        Mockito.`when`(apiManager.getImageList(1)).thenReturn(Observable.just(photoListApi))
    }

    @Test
    fun getPhoto() {
        photoDataRepository.getPhoto(1)
        verify(apiManager).getImageList(1)
    }
}