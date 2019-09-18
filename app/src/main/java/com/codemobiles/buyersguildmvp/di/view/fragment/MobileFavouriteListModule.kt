package com.codemobiles.buyersguildmvp.di.view.fragment

import com.codemobiles.buyersguildmvp.fragment.FavouriteListFragment
import com.codemobiles.domain.usecase.favorite.RemoveFavouriteUseCase
import com.codemobiles.presentation.presenter.MobileFavouriteListPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MobileFavouriteListModule {
    @Binds
    abstract fun provideContext(fragment: FavouriteListFragment): FavouriteListFragment

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providePresenter(removeFavouriteUseCase: RemoveFavouriteUseCase): MobileFavouriteListPresenter {
            return MobileFavouriteListPresenter(removeFavouriteUseCase)
        }
    }
}