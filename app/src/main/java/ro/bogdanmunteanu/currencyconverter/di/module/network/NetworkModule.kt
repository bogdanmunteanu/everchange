package ro.bogdanmunteanu.currencyconverter.di.module.network

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ro.bogdanmunteanu.currencyconverter.BuildConfig
import ro.bogdanmunteanu.currencyconverter.data.api.NetworkInfo
import ro.bogdanmunteanu.currencyconverter.data.api.OfflineException
import ro.bogdanmunteanu.currencyconverter.data.di.RevolutApiService
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private val TAG = NetworkModule::class.simpleName
        private const val OFFLINE_INTERCEPTOR = "offlineInterceptor"
        private const val BASE_URL = "baseUrl"
    }

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return  "https://revolut.duckdns.org"
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
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor? = null,
                          @Named(OFFLINE_INTERCEPTOR)offlineCheckInterceptor: Interceptor? = null):OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()
        if (loggingInterceptor != null) okHttpClient.addInterceptor(loggingInterceptor)
        if (offlineCheckInterceptor != null)  okHttpClient.addInterceptor(offlineCheckInterceptor)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@Named(BASE_URL) baseUrl: String,gson: Gson, httpClient: OkHttpClient) : Retrofit {
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

    @Provides
    @Singleton
    @Named(OFFLINE_INTERCEPTOR)
    fun provideOfflineCheckInterceptor(networkInfo: NetworkInfo): Interceptor {
        return Interceptor { chain ->
            if (networkInfo.isNetworkAvailable()) {
                chain.proceed(chain.request())
            } else {
                throw OfflineException()
            }
        }
    }


}