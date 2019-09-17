package com.codemobiles.presentation.presenter

import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.model.PhotoListModel
import com.codemobiles.domain.usecase.photoList.GetPhotoUseCase
import com.codemobiles.presentation.view.DetailVIew
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class DetailPresenterTest {

    private var view: DetailVIew = mock()
    private var getPhotoUseCase: GetPhotoUseCase = mock()
    private var detailPresenter = DetailPresenter(getPhotoUseCase)
    private val photoListModel = arrayListOf<PhotoListModel>()
    private val mobileModel = MobileModel("test","test",1,"test",3000.0,4.5,"test",false)


    @Before
    fun setUp() {
        detailPresenter.setView(view)
        Mockito.`when`(getPhotoUseCase.getObservable(1)).thenReturn(Observable.just(mock()))
        photoListModel.add(PhotoListModel(1,1,"test"))
    }

    @Test
    fun getPassData() {
        detailPresenter.getPassData(mobileModel)
//        verify(view).setName(mobileModel.name)
//        verify(view).setName(mobileModel.brand)
//        verify(view).setName(mobileModel.description)
//        verify(view).setName(mobileModel.price.toString())
//        verify(view).setName(mobileModel.rating.toString())
    }

    @Test
    fun `feedImageDetail success`() {
        val argumentCaptor = argumentCaptor<DisposableObserver<List<PhotoListModel>>>()
        detailPresenter.feedImageDetail(1)
        verify(getPhotoUseCase).execute(argumentCaptor.capture(), any())
        argumentCaptor.firstValue.onNext(photoListModel)
        verify(view).setImageList(photoListModel)
    }

    @Test
    fun `feedImageDetail fail`() {
        val argumentCaptor = argumentCaptor<DisposableObserver<List<PhotoListModel>>>()
        detailPresenter.feedImageDetail(2)
        verify(getPhotoUseCase).execute(argumentCaptor.capture(), any())
        argumentCaptor.firstValue.onError(Throwable())
        verify(view).setImageList(isNull())
    }
}