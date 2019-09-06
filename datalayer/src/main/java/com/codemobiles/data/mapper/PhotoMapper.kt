package com.codemobiles.data.mapper

import com.codemobiles.buyersguildmvp.model.PhotoListResponse
import com.codemobiles.domain.model.PhotoListModel

class PhotoMapper {
    fun transformApiToDataList(dataList: List<PhotoListResponse>): List<PhotoListModel> {
        val itemArray = ArrayList<PhotoListModel>()
        dataList.mapTo(itemArray) { item ->
            trasnformApiToData(item)
        }
        return itemArray
    }

    fun trasnformApiToData(data: PhotoListResponse): PhotoListModel {
        return PhotoListModel(data.id, data.mobile_id, data.url)
    }
}