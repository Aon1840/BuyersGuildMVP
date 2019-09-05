package com.codemobiles.buyersguildmvp.presenter

import com.codemobiles.buyersguildmvp.PRICE_HIGHTOLOW
import com.codemobiles.buyersguildmvp.PRICE_LOWTOHIGH
import com.codemobiles.buyersguildmvp.RATE_5_1
import com.codemobiles.buyersguildmvp.api.ApiInterface
import com.codemobiles.buyersguildmvp.contract.MobileListView
import com.codemobiles.buyersguildmvp.database.MobileDAO
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.Observer
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MobileListPresenterTest {

    private var mApiManager: ApiInterface = mock()
    private var mFavouriteMobileDAO: MobileDAO = mock()
    private var mView: MobileListView = mock()
    private var mPresenter = MobileListPresenter(mApiManager, mFavouriteMobileDAO)
    private var mArray = arrayListOf<MobileResponse>()
    private var mArraySortByPriceLowToHight = arrayListOf<MobileResponse>()
    private var mArraySortByPriceHightToLow = arrayListOf<MobileResponse>()
    private var mArraySortByRating = arrayListOf<MobileResponse>()
    private var mArrayFavourite = arrayListOf<MobileResponse>()
    private var mArrayCurrentFavourite = arrayListOf<MobileResponse>()

    // access first
    @Before
    fun setUp() {
        mPresenter.setView(mView)

        mArray.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 1,
                price = 179.99,
                rating = 4.9,
                name = "phone1",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArray.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 2,
                price = 79.99,
                rating = 3.9,
                name = "phone2",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArray.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 3,
                price = 109.99,
                rating = 1.9,
                name = "phone3",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArray.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 4,
                price = 19.99,
                rating = 5.0,
                name = "phone4",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArray.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 5,
                price = 149.99,
                rating = 4.3,
                name = "phone5",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )

        mArrayFavourite.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 2,
                price = 79.99,
                rating = 3.9,
                name = "phone2",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArrayFavourite.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 4,
                price = 19.99,
                rating = 5.0,
                name = "phone4",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )

        mArrayCurrentFavourite.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 1,
                price = 179.99,
                rating = 4.9,
                name = "phone1",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArrayCurrentFavourite.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 2,
                price = 79.99,
                rating = 3.9,
                name = "phone2",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = true
            )
        )
        mArrayCurrentFavourite.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 3,
                price = 109.99,
                rating = 1.9,
                name = "phone3",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArrayCurrentFavourite.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 4,
                price = 19.99,
                rating = 5.0,
                name = "phone4",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = true
            )
        )
        mArrayCurrentFavourite.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 5,
                price = 149.99,
                rating = 4.3,
                name = "phone5",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )

        mArraySortByPriceLowToHight.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 4,
                price = 19.99,
                rating = 5.0,
                name = "phone4",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArraySortByPriceLowToHight.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 2,
                price = 79.99,
                rating = 3.9,
                name = "phone2",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArraySortByPriceLowToHight.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 3,
                price = 109.99,
                rating = 1.9,
                name = "phone3",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArraySortByPriceLowToHight.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 5,
                price = 149.99,
                rating = 4.3,
                name = "phone5",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArraySortByPriceLowToHight.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 1,
                price = 179.99,
                rating = 4.9,
                name = "phone1",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )

        mArraySortByPriceHightToLow.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 1,
                price = 179.99,
                rating = 4.9,
                name = "phone1",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArraySortByPriceHightToLow.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 5,
                price = 149.99,
                rating = 4.3,
                name = "phone5",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArraySortByPriceHightToLow.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 3,
                price = 109.99,
                rating = 1.9,
                name = "phone3",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArraySortByPriceHightToLow.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 2,
                price = 79.99,
                rating = 3.9,
                name = "phone2",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArraySortByPriceHightToLow.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 4,
                price = 19.99,
                rating = 5.0,
                name = "phone4",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )

        mArraySortByRating.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 4,
                price = 19.99,
                rating = 5.0,
                name = "phone4",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArraySortByRating.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 1,
                price = 179.99,
                rating = 4.9,
                name = "phone1",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArraySortByRating.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 5,
                price = 149.99,
                rating = 4.3,
                name = "phone5",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArraySortByRating.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 2,
                price = 79.99,
                rating = 3.9,
                name = "phone2",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
        mArraySortByRating.add(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 3,
                price = 109.99,
                rating = 1.9,
                name = "phone3",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
    }

    @Test
    fun feedMobileList() {
        val observable: Observable<List<MobileResponse>> = mock()
        val observer = argumentCaptor<Observer<List<MobileResponse>>>()
        val result = argumentCaptor<ArrayList<MobileResponse>>()
        whenever(mApiManager.getPhones()).thenReturn(observable)
        mPresenter.feedMobileList()
        verify(observable).subscribe(observer.capture())
        observer.firstValue.onNext(listOf(mock(), mock()))
        verify(mView).showMobileList(result.capture())
        verify(mView).setPreFavourite()
        Assert.assertEquals(result.firstValue.size, 2)

    }

    @Test
    fun sortMobileByPriceLowToHigh() {
        mPresenter.sortMobile(mock(), PRICE_LOWTOHIGH)
        verify(mView).showMobileList(any())
    }

    @Test
    fun sortMobileByHighToLow() {
        mPresenter.sortMobile(mArray, PRICE_HIGHTOLOW)
        verify(mView).showMobileList(mArraySortByPriceHightToLow)
    }

    @Test
    fun sortMobileByRating() {
        mPresenter.sortMobile(mArray, RATE_5_1)
        verify(mView).showMobileList(mArraySortByRating)
    }

    @Test
    fun getCurrentFav() {
        mPresenter.getCurrentFav(mArray, mArrayFavourite)
        verify(mView).showMobileList(mArrayCurrentFavourite)
    }

    @Test
    fun addFavoriteMobile() {

        mPresenter.addFavoriteMobile(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 2,
                price = 79.99,
                rating = 3.9,
                name = "phone2",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )

    }

    @Test
    fun removeFavoriteMobile() {
        mPresenter.removeFavoriteMobile(
            MobileResponse(
                brand = "samsung",
                description = "blabla",
                id = 2,
                price = 79.99,
                rating = 3.9,
                name = "phone2",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = false
            )
        )
    }

    @Test
    fun getFavouriteMobile() {
        mPresenter.getFavouriteMobile()
    }

    @Test
    fun checkFavourite() {
        mPresenter.checkFavourite()
    }

}