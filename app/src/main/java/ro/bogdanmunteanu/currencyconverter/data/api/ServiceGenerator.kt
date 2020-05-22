package ro.bogdanmunteanu.currencyconverter.data.api

import androidx.annotation.NonNull
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceGenerator @Inject constructor(val builder: Retrofit.Builder) {

    fun <S> createService(@NonNull baseUrl: String, serviceClass: Class<S>): S {
        return builder.baseUrl(baseUrl).build().create(serviceClass)
    }
}