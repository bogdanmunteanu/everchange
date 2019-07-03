package ro.bogdanmunteanu.currencyconverter.data.di

import retrofit2.http.GET
import retrofit2.http.Path

interface RevolutApiService {

    @GET("https://revolut.duckdns.org/latest?base={baseCurrency}")
    fun getCurrencies(@Path("baseCurrency") baseCurrency: String)

}