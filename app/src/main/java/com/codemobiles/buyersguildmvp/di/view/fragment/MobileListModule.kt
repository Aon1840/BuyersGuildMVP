package com.codemobiles.buyersguildmvp.di.view.fragment

import com.codemobiles.buyersguildmvp.api.ApiInterface
import com.codemobiles.buyersguildmvp.database.MobileDAO
import com.codemobiles.buyersguildmvp.fragment.MobileListFragment
import com.codemobiles.buyersguildmvp.presenter.MobileListPresenter
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
        fun providePresenter(apiInterface: ApiInterface, favouriteMobileDAO: MobileDAO): MobileListPresenter {
            return MobileListPresenter(apiInterface, favouriteMobileDAO)
        }
    }
}