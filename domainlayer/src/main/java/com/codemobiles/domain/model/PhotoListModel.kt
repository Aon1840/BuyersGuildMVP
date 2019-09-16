package com.codemobiles.domain.model

import com.google.gson.annotations.SerializedName

data class PhotoListModel(
    val id: Int,
    @SerializedName("mobile_id") val mobileId: Int,
    var url: String
)