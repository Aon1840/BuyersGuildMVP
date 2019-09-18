package com.codemobiles.domain.usecase.photoList

import com.codemobiles.domain.model.PhotoListModel
import com.codemobiles.domain.repository.PhotoRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetPhotoUseCaseTest {

    private var photoRepository: PhotoRepository = mock()
    private var getPhotoUseCase: GetPhotoUseCase = mock()
    private var dataList = ArrayList<PhotoListModel>()

    @Before
    fun setUp() {
        getPhotoUseCase = GetPhotoUseCase(photoRepository)
        dataList.add(PhotoListModel(1, 1, "test"))
    }

    @Test
    fun getObservable() {
        whenever(photoRepository.getPhoto(1)).thenReturn(Observable.just(dataList))
        getPhotoUseCase.getObservable(1).test().assertComplete()
    }
}