package com.codemobiles.presentation.presenter

import com.codemobiles.domain.model.MobileModel
import com.codemobiles.domain.usecase.favorite.RemoveFavouriteUseCase
import com.codemobiles.presentation.PRICE_HIGHTOLOW
import com.codemobiles.presentation.PRICE_LOWTOHIGH
import com.codemobiles.presentation.RATE_5_1
import com.codemobiles.presentation.view.FavouriteListView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MobileFavouriteListPresenterTest {

    private var view: FavouriteListView = mock()
    private var removeFavouriteUseCase: RemoveFavouriteUseCase = mock()
    private var favouriteListPresenter = MobileFavouriteListPresenter(removeFavouriteUseCase)
    private var mArraySortByPriceLowToHight = arrayListOf<MobileModel>()
    private var mArraySortByPriceHightToLow = arrayListOf<MobileModel>()
    private var mArraySortByRating = arrayListOf<MobileModel>()
    private var mArrayFavourite = arrayListOf<MobileModel>()
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
        favouriteListPresenter.setView(view)
        mArrayFavourite.add(
            MobileModel(
                brand = "samsung",
                description = "blabla",
                id = 1,
                price = 179.99,
                rating = 4.9,
                name = "phone1",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = true
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
                fav = true
            )
        )
        mArrayFavourite.add(
            MobileModel(
                brand = "samsung",
                description = "blabla",
                id = 3,
                price = 109.99,
                rating = 1.9,
                name = "phone3",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = true
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
                fav = true
            )
        )
        mArrayFavourite.add(
            MobileModel(
                brand = "samsung",
                description = "blabla",
                id = 5,
                price = 149.99,
                rating = 4.3,
                name = "phone5",
                thumbImageURL = "https://www.91-img.com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg",
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
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
                fav = true
            )
        )
    }

    @Test
    fun removeMobileFav() {
        Mockito.`when`(removeFavouriteUseCase.getObservable(mock())).thenReturn(Observable.just(0))
        val argumentCaptor = argumentCaptor<DisposableObserver<Int>>()
        favouriteListPresenter.removeMobileFav(mArrayFavourite,1)
        verify(removeFavouriteUseCase).execute(argumentCaptor.capture(), any())
        argumentCaptor.firstValue.onNext(1)
        view.showMobileFav(any())
    }

    @Test
    fun `removeMobileFav fail`() {
        Mockito.`when`(removeFavouriteUseCase.getObservable(mock())).thenReturn(Observable.just(0))
        val argumentCaptor = argumentCaptor<DisposableObserver<Int>>()
        favouriteListPresenter.removeMobileFav(mArrayFavourite,1)
        verify(removeFavouriteUseCase).execute(argumentCaptor.capture(), any())
        argumentCaptor.firstValue.onError(Throwable())
    }

    @Test
    fun `removeMobileFav complete`() {
        Mockito.`when`(removeFavouriteUseCase.getObservable(mock())).thenReturn(Observable.just(0))
        val argumentCaptor = argumentCaptor<DisposableObserver<Int>>()
        favouriteListPresenter.removeMobileFav(mArrayFavourite,1)
        verify(removeFavouriteUseCase).execute(argumentCaptor.capture(), any())
        argumentCaptor.firstValue.onComplete()
    }

    @Test
    fun `sort by price low to high`() {
        favouriteListPresenter.sortMobile(mArrayFavourite, PRICE_LOWTOHIGH)
        verify(view).showMobileFav(mArraySortByPriceLowToHight)
    }

    @Test
    fun `sort by price height to low`() {
        favouriteListPresenter.sortMobile(mArrayFavourite, PRICE_HIGHTOLOW)
        verify(view).showMobileFav(mArraySortByPriceHightToLow)
    }

    @Test
    fun `sort by rating`() {
        favouriteListPresenter.sortMobile(mArrayFavourite, RATE_5_1)
        verify(view).showMobileFav(mArraySortByRating)
    }

    @Test
    fun setMobileFav() {
        favouriteListPresenter.setMobileFav(mArrayFavourite)
        verify(view).showMobileFav(mArrayFavourite)
    }
}