package com.codemobiles.buyersguildmvp.di.view.fragment

import com.codemobiles.buyersguildmvp.database.MobileDAO
import com.codemobiles.buyersguildmvp.fragment.FavouriteListFragment
import com.codemobiles.domain.usecase.favorite.AddFavouriteUseCase
import com.codemobiles.domain.usecase.favorite.RemoveFavouriteUseCase
import com.codemobiles.domain.usecase.mobileList.GetPhoneFavouriteListUseCase
import com.codemobiles.domain.usecase.mobileList.GetPhoneListUseCase
import com.codemobiles.presentation.presenter.MobileFavouriteListPresenter
import com.codemobiles.presentation.presenter.MobileListPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MobileFavouriteListModule {
    @Binds
    abstract fun provideContext(fragment: FavouriteListFragment): FavouriteListFragment

    @Module
    companion object {
        @JvmStatic @Provides
//        fun providePresenter(favouriteMobileDAO: MobileDAO): MobileFavouriteListPresenter {
////            return MobileFavouriteListPresenter(favouriteMobileDAO)
////        }
        fun providePresenter(removeFavouriteUseCase: RemoveFavouriteUseCase) : MobileFavouriteListPresenter {
            return MobileFavouriteListPresenter(removeFavouriteUseCase)
        }
    }
}