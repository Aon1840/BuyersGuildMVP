package com.codemobiles.presentation.presenter

import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.usecase.favorite.AddFavouriteUseCase
import com.codemobiles.domain.usecase.favorite.RemoveFavouriteUseCase
import com.codemobiles.domain.usecase.mobileList.GetPhoneFavouriteListUseCase
import com.codemobiles.domain.usecase.mobileList.GetPhoneListUseCase
import com.codemobiles.presentation.PRICE_HIGHTOLOW
import com.codemobiles.presentation.PRICE_LOWTOHIGH
import com.codemobiles.presentation.RATE_5_1
import com.codemobiles.presentation.view.MobileListView
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class MobileListPresenterTest {

    private var view: MobileListView = mock()
    private var getPhoneListUseCase: GetPhoneListUseCase = mock()
    private var addFavouriteUseCase: AddFavouriteUseCase = mock()
    private var removeFavouriteUseCase: RemoveFavouriteUseCase = mock()
    private var getPhoneFavouriteListUseCase: GetPhoneFavouriteListUseCase = mock()
    private var mobileListPresenter = MobileListPresenter(
        getPhoneListUseCase,
        addFavouriteUseCase,
        removeFavouriteUseCase,
        getPhoneFavouriteListUseCase
    )
    private var mArray = arrayListOf<MobileModel>()
    private var mArraySortByPriceLowToHight = arrayListOf<MobileModel>()
    private var mArraySortByPriceHightToLow = arrayListOf<MobileModel>()
    private var mArraySortByRating = arrayListOf<MobileModel>()
    private var mArrayFavourite = arrayListOf<MobileModel>()
    private var mArrayCurrentFavourite = arrayListOf<MobileModel>()
    private var mData = MobileModel(
        brand = "samsung",
        description = "blabla",
        id = 2,
        price = 79.99,
        rating = 3.9,
        name = "phone2",
        thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
        fav = false
    )

    @Before
    fun setUp() {
        mobileListPresenter.setView(view)
        mArray.add(
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
            MobileModel(
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
    fun `feedMobileList success`() {
        Mockito.`when`(getPhoneListUseCase.getObservable(isNull())).thenReturn(Observable.just(mArray))
        val argumentCaptor = argumentCaptor<DisposableObserver<List<MobileModel>>>()
        mobileListPresenter.feedMobileList()
        verify(getPhoneListUseCase).execute(argumentCaptor.capture(), isNull())
        argumentCaptor.firstValue.onNext(mArray)
        verify(view).showMobileList(mArray)
    }

    @Test
    fun `feedMobileList fail`() {
        Mockito.`when`(getPhoneListUseCase.getObservable(isNull())).thenReturn(null)
        val argumentCaptor = argumentCaptor<DisposableObserver<List<MobileModel>>>()
        mobileListPresenter.feedMobileList()
        verify(getPhoneListUseCase).execute(argumentCaptor.capture(), isNull())
        argumentCaptor.firstValue.onError(Throwable())
    }

    @Test
    fun `sort by price low to high`() {
        mobileListPresenter.sortMobile(mArray, PRICE_LOWTOHIGH)
        verify(view).showMobileList(mArraySortByPriceLowToHight)
    }

    @Test
    fun `sort by price height to low`() {
        mobileListPresenter.sortMobile(mArray, PRICE_HIGHTOLOW)
        verify(view).showMobileList(mArraySortByPriceHightToLow)
    }

    @Test
    fun `sort by rating`() {
        mobileListPresenter.sortMobile(mArray, RATE_5_1)
        verify(view).showMobileList(mArraySortByRating)
    }

    @Test
    fun getCurrentFav() {
        mobileListPresenter.getCurrentFav(mArray, mArrayFavourite)
        verify(view).showMobileList(mArrayCurrentFavourite)
    }

    @Test
    fun addFavoriteMobile() {
        Mockito.`when`(addFavouriteUseCase.getObservable(mData)).thenReturn(Observable.just(0))
        val argumentCaptor = argumentCaptor<DisposableObserver<Int>>()
        mobileListPresenter.addFavoriteMobile(mData)
        verify(addFavouriteUseCase).execute(argumentCaptor.capture(), any())
        argumentCaptor.firstValue.onNext(any())
    }

    @Test
    fun removeFavoriteMobile() {
        Mockito.`when`(removeFavouriteUseCase.getObservable(mData)).thenReturn(Observable.just(0))
        val argumentCaptor = argumentCaptor<DisposableObserver<Int>>()
        mobileListPresenter.removeFavoriteMobile(mData)
        verify(removeFavouriteUseCase).execute(argumentCaptor.capture(), any())
        argumentCaptor.firstValue.onNext(any())
    }

    @Test
    fun getFavouriteMobile() {
        mobileListPresenter.getFavouriteMobile()
    }

    @Test
    fun checkFavourite() {
        Mockito.`when`(getPhoneFavouriteListUseCase.getObservable(isNull())).thenReturn(Observable.just(mArrayFavourite))
        val argumentCaptor = argumentCaptor<DisposableObserver<List<MobileModel>>>()
        mobileListPresenter.checkFavourite()
        verify(getPhoneFavouriteListUseCase).execute(argumentCaptor.capture(), isNull())
        argumentCaptor.firstValue.onNext(mArrayFavourite)
    }
}