package com.codemobiles.domain.usecase.mobileList

import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.repository.MobileRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetPhoneFavouriteListUseCaseTest {

    private var mobileRepository: MobileRepository = mock()
    private var getPhoneFavouriteListUseCase: GetPhoneFavouriteListUseCase = mock()
    private var dataList = arrayListOf<MobileModel>()

    @Before
    fun setUp() {
        getPhoneFavouriteListUseCase = GetPhoneFavouriteListUseCase(mobileRepository)
        dataList.add(MobileModel("test", "test", 1, "test", 30.5, 4.5, "test", false))
    }

    @Test
    fun getObservable() {
        whenever(mobileRepository.getPhoneFavouriteList()).thenReturn(Observable.just(dataList))
        getPhoneFavouriteListUseCase.getObservable(null).test().assertComplete()
    }
}