package ro.bogdanmunteanu.currencyconverter.di.module

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ro.bogdanmunteanu.currencyconverter.BuildConfig
import ro.bogdanmunteanu.currencyconverter.data.api.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class NetworkModule {
    companion object {
        private val TAG = NetworkModule::class.simpleName
        private const val OFFLINE_INTERCEPTOR = "offlineInterceptor"
        private const val BASE_URL = "baseUrl"
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideHttpClient(context: Context,isNetworkAvailable:Boolean): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val client = OkHttpClient.Builder()
            .cache(mCache) // make your app offline-friendly without a database!
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(OkHttpProfilerInterceptor())
            .addInterceptor { chain ->
                var request = chain.request()
                /* If there is Internet, get the cache that was stored 5 seconds ago.
                 * If the cache is older than 5 seconds, then discard it,
                 * and indicate an error in fetching the response.
                 * The 'max-age' attribute is responsible for this behavior.
                 */
                request = if (isNetworkAvailable) request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                /*If there is no Internet, get the cache that was stored 7 days ago.
                 * If the cache is older than 7 days, then discard it,
                 * and indicate an error in fetching the response.
                 * The 'max-stale' attribute is responsible for this behavior.
                 * The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                 */
                else request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
        return client.build()
    }

    @Provides
    @Singleton
    fun providesRetrofitBuilder(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)

    }

    @Provides
    @Singleton
    fun provideIsNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: android.net.NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    @Provides
    @Singleton
    fun provideRevolutApiService(builder: Retrofit.Builder): RevolutApiService =
        ServiceGenerator(builder).createService(
            ApiConstants.BASE_URL, RevolutApiService::class.java
        )
}