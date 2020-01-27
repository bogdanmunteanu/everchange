package ro.bogdanmunteanu.currencyconverter.persistence

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import io.reactivex.Observable
import ro.bogdanmunteanu.currencyconverter.data.model.Currency
import javax.inject.Inject

class CurrencyRepository @Inject constructor(database: CurrencyDatabase) {


    private var currencyDao : CurrencyDao = database.currencyDao()

    private var allCurrencies : List<Currency>

    init {
        allCurrencies = currencyDao.getAllCurrencies()
    }

    fun insert(currency: Currency) = currencyDao.insert(currency)
    fun update(currency: Currency) = currencyDao.update(currency)
    fun delete(currency: Currency) = currencyDao.delete(currency)
    fun deleteAllCurrencies() = currencyDao.deleteAllCurrencies()
    fun getCurrency(currency: Currency) = currencyDao.getCurrency(currency.isoCode)

    fun getAllCurrencies(): List<Currency> {
        return allCurrencies
    }
}