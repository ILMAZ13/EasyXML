package ru.kpfu.easyxml.di.modules

import com.github.salomonbrys.kodein.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.kpfu.easyxml.modules.network.Api

val netModule = Kodein.Module {
    constant("baseUrl") with "https://api.figma.com/"

    bind<Interceptor>() with singleton {
        HttpLoggingInterceptor { println(it) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    bind<Gson>() with singleton { GsonBuilder().create() }
    bind<OkHttpClient>() with singleton {
        OkHttpClient.Builder()
                .addInterceptor(instance())
                .build()
    }
    bind<Retrofit>() with singleton {
        Retrofit.Builder()
                .client(instance())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(instance()))
                .baseUrl(instance<String>("baseUrl"))
                .build()
    }
    bind<Api>() with singleton { instance<Retrofit>().create(Api::class.java) }
}