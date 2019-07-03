package ro.bogdanmunteanu.currencyconverter.di.module.network

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ro.bogdanmunteanu.currencyconverter.BuildConfig
import ro.bogdanmunteanu.currencyconverter.data.api.NetworkInfo
import ro.bogdanmunteanu.currencyconverter.data.di.RevolutApiService
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private val TAG = NetworkModule::class.simpleName
        private const val OFFLINE_INTERCEPTOR = "offlineInterceptor"
        private const val BASE_URL = "https://revolut.duckdns.org"
    }

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return BASE_URL
    }


    @Provides
    @Singleton
    fun provideNetworkInfo(context: Context): NetworkInfo {
        return NetworkInfo(context)
    }

    @Provides
    @Singleton
    fun provideHttpLoginInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val level = getInterceptorLevel()
        httpLoggingInterceptor.level = level
        return httpLoggingInterceptor
    }

    private fun getInterceptorLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory() : GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }

    @Provides
    @Singleton
    fun provideGson() = Gson()



    @Provides
    @Singleton
    fun provideRetrofit(@Named(BASE_URL) baseUrl: String, gson: Gson, httpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : RevolutApiService = retrofit.create(RevolutApiService::class.java)
}