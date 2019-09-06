package com.codemobiles.domain.usecase.photoList

import com.codemobiles.domain.model.PhotoListModel
import com.codemobiles.domain.repository.PhotoRepository
import com.codemobiles.domain.usecase.BaseUseCase
import io.reactivex.Observable

class GetPhotoUseCase constructor(private val phoneImageRepository: PhotoRepository) :
    BaseUseCase<List<PhotoListModel>, Int>() {
    override fun getObservable(params: Int?): Observable<List<PhotoListModel>> {
        return phoneImageRepository.getPhoto(params!!)
    }
}