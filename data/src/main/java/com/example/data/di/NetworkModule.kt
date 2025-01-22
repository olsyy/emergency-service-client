package com.example.data.di

import com.example.common.constants.AppConstants.BASE_URL
import com.example.common.constants.AppConstants.TIMEOUT
import com.example.data.api.IncidentApi
import com.example.data.api.ZoneApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder().build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun provideOkhttp(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideIncidentApi(retrofit: Retrofit): IncidentApi {
        return retrofit.create(IncidentApi::class.java)
    }

    @Provides
    @Singleton
    fun provideZoneApi(retrofit: Retrofit): ZoneApi {
        return retrofit.create(ZoneApi::class.java)
    }
}