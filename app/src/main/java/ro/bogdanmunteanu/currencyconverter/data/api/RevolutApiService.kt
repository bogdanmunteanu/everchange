package ro.bogdanmunteanu.currencyconverter.data.di

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ro.bogdanmunteanu.currencyconverter.data.model.Currencies
import java.util.*

interface RevolutApiService {

    @GET("/latest?")
    fun getCurrencies(@Query("base") baseCurrency: String) : Flowable<Currencies>

}