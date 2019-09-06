package com.codemobiles.data.dataRepository

import com.codemobiles.data.mapper.MobileEntityDataMapper
import com.codemobiles.data.mapper.PhotoMapper
import com.codemobiles.data.network.ApiInterface
import com.codemobiles.domain.model.PhotoListModel
import com.codemobiles.domain.repository.PhotoRepository
import io.reactivex.Observable

class MobileDataRepository constructor(
    var apiManager: ApiInterface, var photoDataMapper: PhotoMapper) : PhotoRepository {

    override fun getPhoto(id: Int): Observable<List<PhotoListModel>> {
        return apiManager.getImageList(id).concatMap { r ->
            return@concatMap Observable.just(photoDataMapper.transformApiToDataList(r))
        }
    }
}