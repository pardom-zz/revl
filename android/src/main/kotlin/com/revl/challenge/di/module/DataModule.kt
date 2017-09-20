package com.revl.challenge.di.module

import android.content.Context
import com.revl.challenge.retrofit.image.ImageService
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module(includes = arrayOf(
        RemoteDatasourceModule::class
))
class DataModule {

    @Provides
    fun picasso(context: Context): Picasso {
        return Picasso.with(context)
    }

    @Provides
    @Named("baseUrl")
    fun baseUrl(): String {
        // TODO: This is not the ideal place to provide the base url. Typically it would be provided
        // by a condition based abstraction, e.g. mock mode, production, staging, etc.
        return "https://api.imgur.com/3/image/"
    }

    @Provides
    fun loggerLevel(): Level {
        // TODO: This is not the ideal place to provide the logger level. Typically it would be
        // provided by a condition based abstraction, e.g. mock mode, production, staging, etc.
        return Level.BODY
    }

    @Provides
    fun okHttp(logLevel: Level): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = logLevel
                })
                .build()
    }

    @Provides
    fun retrofit(
            client: OkHttpClient,
            @Named("baseUrl") baseUrl: String): Retrofit {

        return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    @Provides
    fun imageService(retrofit: Retrofit): ImageService {
        return retrofit.create(ImageService::class.java)
    }

}
