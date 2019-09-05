package com.codemobiles.buyersguildmvp.presenter

import com.codemobiles.buyersguildmvp.PRICE_HIGHTOLOW
import com.codemobiles.buyersguildmvp.PRICE_LOWTOHIGH
import com.codemobiles.buyersguildmvp.RATE_5_1
import com.codemobiles.buyersguildmvp.contract.FavouriteListView
import com.codemobiles.buyersguildmvp.database.MobileDAO
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class MobileFavouriteListPresenterTest {

    private var mView: FavouriteListView = mock()
    private var mFavouriteMobileDAO: MobileDAO = mock()
    private var mPresenter = MobileFavouriteListPresenter(mFavouriteMobileDAO)
    private var mArray = arrayListOf<MobileResponse>()
    private var mArraySortByPriceLowToHight = arrayListOf<MobileResponse>()
    private var mArraySortByPriceHightToLow = arrayListOf<MobileResponse>()
    private var mArraySortByRating = arrayListOf<MobileResponse>()
    private var mArrayRemoveUpdate = arrayListOf<MobileResponse>()

    @Before
    fun setUp() {
        mPresenter.setView(mView)

        mArray.add(MobileResponse(brand = "samsung", description = "blabla", id = 1, price = 179.99, rating = 4.9, name = "phone1", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArray.add(MobileResponse(brand = "samsung", description = "blabla", id = 2, price = 79.99, rating = 3.9, name = "phone2", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArray.add(MobileResponse(brand = "samsung", description = "blabla", id = 3, price = 109.99, rating = 1.9, name = "phone3", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArray.add(MobileResponse(brand = "samsung", description = "blabla", id = 4, price = 19.99, rating = 5.0, name = "phone4", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArray.add(MobileResponse(brand = "samsung", description = "blabla", id = 5, price = 149.99, rating = 4.3, name = "phone5", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))

        mArrayRemoveUpdate.add(MobileResponse(brand = "samsung", description = "blabla", id = 1, price = 179.99, rating = 4.9, name = "phone1", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArrayRemoveUpdate.add(MobileResponse(brand = "samsung", description = "blabla", id = 2, price = 79.99, rating = 3.9, name = "phone2", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArrayRemoveUpdate.add(MobileResponse(brand = "samsung", description = "blabla", id = 3, price = 109.99, rating = 1.9, name = "phone3", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArrayRemoveUpdate.add(MobileResponse(brand = "samsung", description = "blabla", id = 4, price = 19.99, rating = 5.0, name = "phone4", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))

        mArraySortByPriceLowToHight.add(MobileResponse(brand = "samsung", description = "blabla", id = 4, price = 19.99, rating = 5.0, name = "phone4", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArraySortByPriceLowToHight.add(MobileResponse(brand = "samsung", description = "blabla", id = 2, price = 79.99, rating = 3.9, name = "phone2", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArraySortByPriceLowToHight.add(MobileResponse(brand = "samsung", description = "blabla", id = 3, price = 109.99, rating = 1.9, name = "phone3", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArraySortByPriceLowToHight.add(MobileResponse(brand = "samsung", description = "blabla", id = 5, price = 149.99, rating = 4.3, name = "phone5", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArraySortByPriceLowToHight.add(MobileResponse(brand = "samsung", description = "blabla", id = 1, price = 179.99, rating = 4.9, name = "phone1", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))

        mArraySortByPriceHightToLow.add(MobileResponse(brand = "samsung", description = "blabla", id = 1, price = 179.99, rating = 4.9, name = "phone1", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArraySortByPriceHightToLow.add(MobileResponse(brand = "samsung", description = "blabla", id = 5, price = 149.99, rating = 4.3, name = "phone5", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArraySortByPriceHightToLow.add(MobileResponse(brand = "samsung", description = "blabla", id = 3, price = 109.99, rating = 1.9, name = "phone3", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArraySortByPriceHightToLow.add(MobileResponse(brand = "samsung", description = "blabla", id = 2, price = 79.99, rating = 3.9, name = "phone2", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArraySortByPriceHightToLow.add(MobileResponse(brand = "samsung", description = "blabla", id = 4, price = 19.99, rating = 5.0, name = "phone4", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))

        mArraySortByRating.add(MobileResponse(brand = "samsung", description = "blabla", id = 4, price = 19.99, rating = 5.0, name = "phone4", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArraySortByRating.add(MobileResponse(brand = "samsung", description = "blabla", id = 1, price = 179.99, rating = 4.9, name = "phone1", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArraySortByRating.add(MobileResponse(brand = "samsung", description = "blabla", id = 5, price = 149.99, rating = 4.3, name = "phone5", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArraySortByRating.add(MobileResponse(brand = "samsung", description = "blabla", id = 2, price = 79.99, rating = 3.9, name = "phone2", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
        mArraySortByRating.add(MobileResponse(brand = "samsung", description = "blabla", id = 3, price = 109.99, rating = 1.9, name = "phone3", thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg", fav = true))
    }

    @Test
    fun removeMobileFav() {
        mPresenter.removeMobileFav(mArray,4)
        verify(mView).showMobileFav(mArrayRemoveUpdate)
    }

    @Test
    fun sortMobileByPriceLowToHigh() {
        mPresenter.sortMobile(mock(), PRICE_LOWTOHIGH)
        verify(mView).showMobileFav(any())
    }

    @Test
    fun sortMobileByHighToLow() {
        mPresenter.sortMobile(mArray, PRICE_HIGHTOLOW)
        verify(mView).showMobileFav(mArraySortByPriceHightToLow)
    }

    @Test
    fun sortMobileByRating() {
        mPresenter.sortMobile(mArray, RATE_5_1)
        verify(mView).showMobileFav(mArraySortByRating)
    }

    @Test
    fun setMobileFav() {
        mPresenter.setMobileFav(mArray)
        verify(mView).showMobileFav(mArray)
    }
}