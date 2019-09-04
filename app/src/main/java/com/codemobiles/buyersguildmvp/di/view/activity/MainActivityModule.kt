package com.codemobiles.buyersguildmvp.di.view.activity

import com.codemobiles.buyersguildmvp.activity.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {
    @Binds
    abstract fun provideContext(activity: MainActivity): MainActivity

}