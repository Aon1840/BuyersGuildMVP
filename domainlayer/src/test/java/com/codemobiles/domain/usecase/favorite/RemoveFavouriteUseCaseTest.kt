package com.codemobiles.domain.usecase.favorite

import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.repository.MobileRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt

class RemoveFavouriteUseCaseTest {

    private var mobileRepository: MobileRepository = mock()
    private var removeFavouriteUseCase = RemoveFavouriteUseCase(mobileRepository)
    private var mobile: MobileModel = mock()


    @Test
    fun getObservable() {
        mobile = MobileModel("test", "test", 1, "test", 30.5, 4.5, "test", false)
        whenever(mobileRepository.removeFavourite(mobile)).thenReturn(Observable.just(anyInt()))
        removeFavouriteUseCase.getObservable(mobile).test().assertComplete()
    }
}