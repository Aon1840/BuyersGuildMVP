package com.codemobiles.buyersguildmvp.di.module

import com.codemobiles.buyersguildmvp.activity.DetailActivity
import com.codemobiles.buyersguildmvp.activity.MainActivity
import com.codemobiles.buyersguildmvp.di.view.activity.DetailActivityModule
import com.codemobiles.buyersguildmvp.di.view.activity.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun detailMobileActivity(): DetailActivity
}