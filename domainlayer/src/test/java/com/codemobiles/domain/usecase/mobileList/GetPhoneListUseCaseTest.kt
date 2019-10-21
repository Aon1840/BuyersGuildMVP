package com.codemobiles.domain.usecase.mobileList

import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.repository.MobileRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetPhoneListUseCaseTest {

    private var mobileRepository: MobileRepository = mock()
    private var getPhoneListUseCase = GetPhoneListUseCase(mobileRepository)
    private var dataList = arrayListOf<MobileModel>()

    @Before
    fun setUp() {
        dataList.add(MobileModel("test", "test", 1, "test", 30.5, 4.5, "test", false))
    }

    @Test
    fun getObservable() {
        whenever(mobileRepository.getPhoneList()).thenReturn(Observable.just(dataList))
        getPhoneListUseCase.getObservable(null).test().assertComplete()
    }
}