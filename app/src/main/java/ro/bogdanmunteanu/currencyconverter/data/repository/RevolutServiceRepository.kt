package ro.bogdanmunteanu.currencyconverter.data.repository

import io.reactivex.Single
import ro.bogdanmunteanu.currencyconverter.data.api.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.model.BaseCurrency
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyResponse
import ro.bogdanmunteanu.currencyconverter.data.model.Currency
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.BaseCurrencyModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyModel
import ro.bogdanmunteanu.currencyconverter.ui.CurrencyMapper
import java.math.BigDecimal
import javax.inject.Inject

class RevolutServiceRepository @Inject constructor(private val apiService: RevolutApiService) {

    fun getCurrenciesFromEndpoint(
        baseCurrency: String,
        input: String = BigDecimal.ONE.toString()
    ): Single<List<CurrencyAbstractModel>> {
        return apiService.getCurrencies(baseCurrency)
            .map { t: CurrencyResponse -> mapCurrencies(t, input) }
    }

    private fun mapCurrencies(
        currencyResponse: CurrencyResponse,
        input: String
    ): List<CurrencyAbstractModel> {
        val mapper: CurrencyMapper = CurrencyMapper.fromTitle(currencyResponse.base)
        val list = ArrayList<CurrencyAbstractModel>()
        try {
            list.add(
                BaseCurrencyModel(
                    BaseCurrency(
                        mapper.title,
                        mapper.subtitle,
                        BigDecimal(input),
                        mapper.flagURL,
                        mapper.priority
                    )
                )
            )
        } catch (e: NumberFormatException) {
            list.add(
                BaseCurrencyModel(
                    BaseCurrency(
                        mapper.title,
                        mapper.subtitle,
                        BigDecimal.ONE,
                        mapper.flagURL,
                        mapper.priority
                    )
                )
            )
        }
        currencyResponse.rates.keys.forEach { key ->
            val mapper: CurrencyMapper = CurrencyMapper.fromTitle(key)
            val currency = CurrencyModel(
                Currency(
                    mapper.title,
                    mapper.subtitle,
                    BigDecimal(currencyResponse.rates[key]),
                    mapper.flagURL,
                    mapper.priority
                )
            )
            list.add(currency)
        }
        return list.toList()
    }
}