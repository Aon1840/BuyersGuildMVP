package com.codemobiles.buyersguildmvp.di.view.activity

import com.codemobiles.buyersguildmvp.activity.DetailActivity
import com.codemobiles.buyersguildmvp.api.ApiInterface
import com.codemobiles.buyersguildmvp.presenter.DetailPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DetailActivityModule {
    @Binds
    abstract fun provideContext(activity: DetailActivity): DetailActivity

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providePresenter(apiInterface: ApiInterface): DetailPresenter {
            return DetailPresenter(apiInterface)
        }
    }
}