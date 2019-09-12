package com.codemobiles.domain.usecase.favorite

import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.repository.MobileRepository
import com.codemobiles.domain.usecase.BaseUseCase
import io.reactivex.Observable

class AddFavouriteUseCase constructor(private val mobileRepository: MobileRepository) :
    BaseUseCase<Int, MobileModel>() {
    override fun getObservable(params: MobileModel?): Observable<Int> {
        return mobileRepository.addFavourite(params!!)
    }
}