package com.codemobiles.buyersguildmvp.model

import android.os.Parcel
import android.os.Parcelable

data class MobileResponse (
    val brand: String,
    val description: String,
    val id: Int,
    val name: String,
    val price: Double,
    val rating: Double,
    val thumbImageURL: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(brand)
        parcel.writeString(description)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeDouble(price)
        parcel.writeDouble(rating)
        parcel.writeString(thumbImageURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MobileResponse> {
        override fun createFromParcel(parcel: Parcel): MobileResponse {
            return MobileResponse(parcel)
        }

        override fun newArray(size: Int): Array<MobileResponse?> {
            return arrayOfNulls(size)
        }
    }
}