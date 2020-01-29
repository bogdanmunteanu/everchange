package ro.bogdanmunteanu.currencyconverter.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyResponse

interface RevolutApiService {

    @GET("/latest?")
    fun getCurrencies(@Query("base") baseCurrency: String): Single<CurrencyResponse>

}