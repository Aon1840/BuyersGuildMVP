package com.codemobiles.domain.usecase.mobileList

import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.repository.MobileRepository
import com.codemobiles.domain.usecase.BaseUseCase
import io.reactivex.Observable

class GetPhoneFavouriteListUseCase constructor(private val mobileRepository: MobileRepository) :
    BaseUseCase<List<MobileModel>, Void>() {
    override fun getObservable(params: Void?): Observable<List<MobileModel>> {
        return mobileRepository.getPhoneFavouriteList()
    }
}