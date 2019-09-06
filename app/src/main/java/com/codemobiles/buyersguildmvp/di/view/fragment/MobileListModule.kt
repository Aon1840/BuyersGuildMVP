package com.codemobiles.buyersguildmvp.di.view.fragment

import com.codemobiles.buyersguildmvp.fragment.MobileListFragment
import com.codemobiles.domain.usecase.favorite.AddFavouriteUseCase
import com.codemobiles.domain.usecase.favorite.RemoveFavouriteUseCase
import com.codemobiles.domain.usecase.mobileList.GetPhoneFavouriteListUseCase
import com.codemobiles.domain.usecase.mobileList.GetPhoneListUseCase
import com.codemobiles.presentation.presenter.MobileListPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MobileListModule {
    @Binds
    abstract fun provideContext(fragment: MobileListFragment): MobileListFragment

    @Module
    companion object {
        @JvmStatic
        @Provides
//        fun providePresenter(apiInterface: ApiInterface, favouriteMobileDAO: MobileDAO): MobileListPresenter {
//            return MobileListPresenter(apiInterface, favouriteMobileDAO)
//        }
        fun providePresenter(getPhoneListUseCase: GetPhoneListUseCase,
                                      addFavouriteUseCase: AddFavouriteUseCase,
                                      removeFavouriteUseCase: RemoveFavouriteUseCase,
                                      getPhoneFavouriteListUseCase: GetPhoneFavouriteListUseCase) : MobileListPresenter {
            return MobileListPresenter(getPhoneListUseCase,addFavouriteUseCase,removeFavouriteUseCase,getPhoneFavouriteListUseCase)
        }
    }
}