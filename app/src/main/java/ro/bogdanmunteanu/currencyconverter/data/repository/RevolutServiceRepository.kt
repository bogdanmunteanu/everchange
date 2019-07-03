package ro.bogdanmunteanu.currencyconverter.data.repository

import io.reactivex.Observable
import ro.bogdanmunteanu.currencyconverter.data.di.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.model.Currencies
import java.util.*
import javax.inject.Inject

class RevolutServiceRepository @Inject constructor(private val apiService: RevolutApiService){

    fun getCurrencies(baseCurrency:String) : Observable<Currencies> {
        return apiService.getCurrencies(baseCurrency)
    }

}