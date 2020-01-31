package ro.bogdanmunteanu.currencyconverter.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ro.bogdanmunteanu.currencyconverter.data.api.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.model.BaseCurrency
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyResponse
import ro.bogdanmunteanu.currencyconverter.data.model.Currency
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.BaseCurrencyModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyModel
import java.math.BigDecimal
import javax.inject.Inject

class RevolutServiceRepository @Inject constructor(private val apiService: RevolutApiService) {

    fun getCurrenciesFromEndpoint(
        baseCurrency: String): Single<CurrencyResponse> {
        return apiService.getCurrencies(baseCurrency).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }


}