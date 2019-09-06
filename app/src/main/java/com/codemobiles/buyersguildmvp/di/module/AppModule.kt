package com.codemobiles.buyersguildmvp.di.module

import android.app.Application
import com.codemobiles.buyersguildmvp.application.MyApplication
import com.codemobiles.buyersguildmvp.database.AppDatabase
import com.codemobiles.buyersguildmvp.database.MobileDAO
import com.codemobiles.buyersguildmvp.model.MobileResponse
import com.codemobiles.buyersguildmvp.model.PhotoListResponse
import com.codemobiles.data.dataRepository.MobileDataRepository
import com.codemobiles.data.dataRepository.PhotoDataRepository
import com.codemobiles.data.mapper.MobileEntityDataMapper
import com.codemobiles.data.mapper.PhotoMapper
import com.codemobiles.data.network.ApiInterface
import com.codemobiles.domain.repository.MobileRepository
import com.codemobiles.domain.repository.PhotoRepository
import com.codemobiles.domain.usecase.favorite.AddFavouriteUseCase
import com.codemobiles.domain.usecase.favorite.RemoveFavouriteUseCase
import com.codemobiles.domain.usecase.mobileList.GetPhoneFavouriteListUseCase
import com.codemobiles.domain.usecase.mobileList.GetPhoneListUseCase
import com.codemobiles.domain.usecase.photoList.GetPhotoUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://scb-test-mobile.herokuapp.com/")
            .build()
    }

//    @Provides
//    @Singleton
//    internal fun provideCallListMobile(retrofit: Retrofit): Observable<List<MobileResponse>> {
//        return retrofit.create(ApiInterface::class.java).getPhones()
//    }
//
//    @Provides
//    @Singleton
//    internal fun provideCallPhoto(id: Int,retrofit: Retrofit): Observable<List<PhotoListResponse>> {
//        return retrofit.create(ApiInterface::class.java).getImageList(id)
//    }

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

    @Provides
    @Singleton
    internal fun provideMobileMapper() : MobileEntityDataMapper {
        return MobileEntityDataMapper()
    }

    @Provides
    @Singleton
    internal fun providePhotoMapper() : PhotoMapper {
        return PhotoMapper()
    }

    @Provides
    @Singleton
    internal fun provideMobileRepository(apiManager: ApiInterface, mobileEntityDataMapper: MobileEntityDataMapper, mobileDAO: MobileDAO) : MobileRepository {
        return MobileDataRepository(apiManager, mobileEntityDataMapper, mobileDAO)
    }

    @Provides
    @Singleton
    internal fun providePhotoRepository(apiManager: ApiInterface, photoMapper: PhotoMapper) : PhotoRepository {
        return PhotoDataRepository(apiManager,photoMapper)
    }

    @Provides
    @Singleton
    internal fun provideAddFavouriteUseCase(mobileRepository: MobileRepository) : AddFavouriteUseCase {
        return AddFavouriteUseCase(mobileRepository)
    }

    @Provides
    @Singleton
    internal fun provideRemoveFavouriteUseCase(mobileRepository: MobileRepository) : RemoveFavouriteUseCase {
        return RemoveFavouriteUseCase(mobileRepository)
    }

    @Provides
    @Singleton
    internal fun provideGetPhoneListUseCase(mobileRepository: MobileRepository) : GetPhoneListUseCase {
        return GetPhoneListUseCase(mobileRepository)
    }

    @Provides
    @Singleton
    internal fun provideGetPhoneFavouriteListUseCase(mobileRepository: MobileRepository) : GetPhoneFavouriteListUseCase {
        return GetPhoneFavouriteListUseCase(mobileRepository)
    }

    @Provides
    @Singleton
    internal fun provideGetPhotoUseCase(photoRepository: PhotoRepository) : GetPhotoUseCase {
        return GetPhotoUseCase(photoRepository)
    }
}