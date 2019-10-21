package com.codemobiles.data.model.api

import com.google.gson.annotations.SerializedName

data class PhotoListResponse(
    val id: Int,
    @SerializedName("mobile_id") val mobileId: Int,
    var url: String
)