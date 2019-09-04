package com.codemobiles.buyersguildmvp.di.components

import com.codemobiles.buyersguildmvp.application.MyApplication
import com.codemobiles.buyersguildmvp.di.module.ActivityBuilderModule
import com.codemobiles.buyersguildmvp.di.module.AppModule
import com.codemobiles.buyersguildmvp.di.module.FragmentBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidSupportInjectionModule::class, ActivityBuilderModule::class, FragmentBuilderModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyApplication)
}