package com.codemobiles.buyersguildmvp.di.module

import com.codemobiles.buyersguildmvp.di.view.fragment.MobileFavouriteListModule
import com.codemobiles.buyersguildmvp.di.view.fragment.MobileListModule
import com.codemobiles.buyersguildmvp.fragment.FavouriteListFragment
import com.codemobiles.buyersguildmvp.fragment.MobileListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector(modules = [MobileFavouriteListModule::class])
    abstract fun favouriteListFragment(): FavouriteListFragment

    @ContributesAndroidInjector(modules = [MobileListModule::class])
    abstract fun mobileListFragment(): MobileListFragment
}