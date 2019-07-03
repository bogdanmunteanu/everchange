package ro.bogdanmunteanu.currencyconverter.data.di

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ro.bogdanmunteanu.currencyconverter.data.model.Currencies
import java.util.*

interface RevolutApiService {

    @GET("https://revolut.duckdns.org/latest?base={baseCurrency}")
    fun getCurrencies(@Path("baseCurrency") baseCurrency: String) : Observable<Currencies>

}