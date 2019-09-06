package com.codemobiles.data.dataRepository

import com.codemobiles.data.mapper.MobileEntityDataMapper
import com.codemobiles.data.mapper.PhotoMapper
import com.codemobiles.data.network.ApiInterface
import com.codemobiles.domain.model.PhotoListModel
import com.codemobiles.domain.repository.PhotoRepository
import io.reactivex.Observable

class PhotoDataRepository constructor(
    var apiManager: ApiInterface, var photoMapper: PhotoMapper) : PhotoRepository {

    override fun getPhoto(id: Int): Observable<List<PhotoListModel>> {
        return apiManager.getImageList(id).concatMap { r ->
            return@concatMap Observable.just(photoMapper.transformApiToDataList(r))
        }
    }
}