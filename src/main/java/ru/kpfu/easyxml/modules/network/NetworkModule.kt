package ru.kpfu.easyxml.modules.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {
    companion object {
        val BASE_URL = "https://api.figma.com/"

        fun getApi(): Api = Retrofit.Builder()
                .client(OkHttpClient.Builder()
                        .addInterceptor(
                                HttpLoggingInterceptor { println(it) }.apply {
                                    level = HttpLoggingInterceptor.Level.BODY
                                })
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build().create(Api::class.java)
    }
}