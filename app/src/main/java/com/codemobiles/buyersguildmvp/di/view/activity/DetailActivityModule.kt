package com.codemobiles.buyersguildmvp.di.view.activity

import com.codemobiles.buyersguildmvp.activity.DetailActivity
import com.codemobiles.domain.usecase.photoList.GetPhotoUseCase
import com.codemobiles.presentation.presenter.DetailPresenter
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
        fun providePresenter(getPhotoUseCase: GetPhotoUseCase): DetailPresenter {
            return DetailPresenter(getPhotoUseCase)
        }
    }
}