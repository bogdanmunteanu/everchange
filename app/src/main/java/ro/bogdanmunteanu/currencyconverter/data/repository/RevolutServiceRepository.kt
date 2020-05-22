package ro.bogdanmunteanu.currencyconverter.data.repository

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ro.bogdanmunteanu.currencyconverter.data.api.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.model.Currency
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyResponse
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel
import ro.bogdanmunteanu.currencyconverter.persistence.CurrencyDao
import ro.bogdanmunteanu.currencyconverter.utils.CurrencyMapper
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RevolutServiceRepository @Inject constructor(private val apiService: RevolutApiService,private val currencyDao: CurrencyDao) {

    private val TAG = RevolutServiceRepository::class.java.simpleName

    fun getCurrencies(base:String) : Observable<CurrencyResponse> = Observable.concat(
        getCurrenciesFromApi(base),getCurrenciesFromDb(base)
    )

    private fun getCurrenciesFromApi(baseCurrency: String): Observable<CurrencyResponse> {
        return apiService.getCurrencies(baseCurrency).subscribeOn(Schedulers.io())
            .delay(1, TimeUnit.SECONDS)
            .repeat()
            .doOnNext {storeCurrenciesInDb(it) }
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getCurrenciesFromDb(base:String):Observable<CurrencyResponse> {
        return currencyDao.getAllCurrencies(base).toObservable()

    }

    private fun storeCurrenciesInDb(currencies:CurrencyResponse){
        Observable.fromCallable { currencyDao.insert(currencies) }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                Log.e(TAG,"inserted currencies into DB")
            }
    }


}