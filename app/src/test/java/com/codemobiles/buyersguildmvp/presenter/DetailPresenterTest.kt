package com.codemobiles.buyersguildmvp.presenter

import com.codemobiles.buyersguildmvp.api.ApiInterface
import com.codemobiles.buyersguildmvp.contract.DetailVIew
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class DetailPresenterTest {

    private var mApiManager: ApiInterface = mock()
    private var mPresenter = DetailPresenter(mApiManager)
    private var mView: DetailVIew = mock()
    private var mData: MobileResponse = MobileResponse(brand = "samsung", description = "blabla", id = 1, price = 179.99, rating = 4.9, name = "phone1", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = false)

    @Before
    fun setUp() {
        mPresenter.setView(mView)
    }

    @Test
    fun getPassData() {
        mPresenter.getPassData(mData)
        verify(mView).setName(mData.name)
        verify(mView).setBrand(mData.brand)
        verify(mView).setDescription(mData.description)
        verify(mView).setPrice(mData.price.toString())
        verify(mView).setRating(mData.rating.toString())
    }

    @Test
    fun feedImageDetail() {
    }
}