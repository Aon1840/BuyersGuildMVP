package com.codemobiles.data.mapper

import com.codemobiles.data.model.api.PhotoListResponse
import com.codemobiles.domain.model.PhotoListModel

class PhotoMapper {
    fun transformApiToDataList(dataList: List<PhotoListResponse>): List<PhotoListModel> {
        return dataList.map { photoResponse ->
            trasnformApiToData(photoResponse)
        }
    }

    fun trasnformApiToData(data: PhotoListResponse): PhotoListModel {
        return PhotoListModel(data.id, data.mobileId, data.url)
    }
}