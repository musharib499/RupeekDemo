package com.app.di

import android.content.Context
import com.app.BuildConfig
import com.app.data.api.ApiHelperImpl
import com.app.data.api.ApiService
import com.app.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Musharib Ali on 11/2/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {
    private var baseUrl = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttp() = run {
        val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
            okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideApiHelper(helper: ApiService) = ApiHelperImpl(helper)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
            AppDatabase.getDatabase(appContext)


    @Singleton
    @Provides
    fun provideCurrencyDao(db: AppDatabase) = db.weatherListDao()

}