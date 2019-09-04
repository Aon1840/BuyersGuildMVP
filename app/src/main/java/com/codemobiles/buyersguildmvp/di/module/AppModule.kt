package com.codemobiles.buyersguildmvp.di.module

import android.app.Application
import com.codemobiles.buyersguildmvp.api.ApiInterface
import com.codemobiles.buyersguildmvp.application.MyApplication
import com.codemobiles.buyersguildmvp.database.AppDatabase
import com.codemobiles.buyersguildmvp.database.MobileDAO
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule{

    private val TIMEOUT = 30

    @Provides
    @Singleton
    fun provideApplicationContext(app: MyApplication): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ssZ")
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val mOkHttpBuilder = OkHttpClient.Builder()
        mOkHttpBuilder.writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        mOkHttpBuilder.readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        mOkHttpBuilder.connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        mOkHttpBuilder.addInterceptor { chain -> chain.proceed(chain.request()) }
        return mOkHttpBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl("https://scb-test-mobile.herokuapp.com/")
            .build()
    }

    @Provides
    @Singleton
    internal fun provideCallListMobile(retrofit: Retrofit): Call<List<MobileResponse>> {
        return retrofit.create(ApiInterface::class.java).getPhones()
    }

    @Provides
    @Singleton
    internal fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    internal fun provideFavouriteMobileDAO(app: Application): MobileDAO {
        //set up database
        val mDatabaseAdapter = app.let {
            AppDatabase.getInstance(it).apply {
                openHelper.readableDatabase
            }
        }
        return mDatabaseAdapter.favoriteDao()
    }


}