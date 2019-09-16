package com.codemobiles.data.mapper

import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.codemobiles.data.model.db.MobileEntity
import com.codemobiles.domain.model.MobileModel

class MobileEntityDataMapper {
    fun transformApiToDataList(dataList: List<MobileResponse>): List<MobileModel> {
        return dataList.map { mobileResponse ->
            trasnformApiToData(mobileResponse)
        }
    }

    fun trasnformApiToData(data: MobileResponse): MobileModel {
        return MobileModel(
            data.brand,
            data.description,
            data.id,
            data.name,
            data.price,
            data.rating,
            data.thumbImageURL,
            data.fav
        )
    }

    fun transformDBToDataList(dataList: List<MobileEntity>): List<MobileModel> {

          return dataList.map { mobileEntity ->
              trasnformDBToData(mobileEntity)
          }
    }

    fun trasnformDBToData(data: MobileEntity): MobileModel {
        return MobileModel(
            data.brand,
            data.description,
            data.id,
            data.name,
            data.price,
            data.rating,
            data.thumbImageURL,
            data.fav
        )
    }
}