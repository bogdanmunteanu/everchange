package ro.bogdanmunteanu.currencyconverter.data.repository

import io.reactivex.Single
import ro.bogdanmunteanu.currencyconverter.data.di.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.model.*
import ro.bogdanmunteanu.currencyconverter.ui.CurrencyMapper
import javax.inject.Inject
import kotlin.collections.ArrayList

class RevolutServiceRepository @Inject constructor(private val apiService: RevolutApiService){

    fun getCurrencies(baseCurrency:String) : Single<List<Any>> {
        return apiService.getCurrencies(baseCurrency).map { t: Currencies -> mapCurrencies(t) }
    }


    fun mapCurrencies(currencies: Currencies) : List<Any>
    {
        var baseCurrency: CurrencyMapper = CurrencyMapper.fromTitle(currencies.base)
        val list = ArrayList<Any>()
        list.add(BaseCurrencyRow(baseCurrency.title,baseCurrency.subtitle,1.00,baseCurrency.flagURL))
        if(currencies.rates.EUR != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.EUR.title,CurrencyMapper.EUR.subtitle,currencies.rates.EUR,CurrencyMapper.EUR.flagURL))
        }
        if(currencies.rates.AUD != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.AUD.title,CurrencyMapper.AUD.subtitle,currencies.rates.AUD,CurrencyMapper.AUD.flagURL))
        }
        if(currencies.rates.BGN != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.BGN.title,CurrencyMapper.BGN.subtitle,currencies.rates.BGN,CurrencyMapper.BGN.flagURL))
        }
        if(currencies.rates.BRL != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.BRL.title,CurrencyMapper.BRL.subtitle,currencies.rates.BRL,CurrencyMapper.BRL.flagURL))
        }
        if(currencies.rates.CAD != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.CAD.title,CurrencyMapper.CAD.subtitle,currencies.rates.CAD,CurrencyMapper.CAD.flagURL))
        }
        if(currencies.rates.CHF != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.CHF.title,CurrencyMapper.CHF.subtitle,currencies.rates.CHF,CurrencyMapper.CHF.flagURL))
        }
        if(currencies.rates.CNY != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.CNY.title,CurrencyMapper.CNY.subtitle,currencies.rates.CNY,CurrencyMapper.CNY.flagURL))
        }
        if(currencies.rates.CZK != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.CZK.title,CurrencyMapper.CZK.subtitle,currencies.rates.CZK,CurrencyMapper.CZK.flagURL))
        }
        if(currencies.rates.DKK != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.DKK.title,CurrencyMapper.DKK.subtitle,currencies.rates.DKK,CurrencyMapper.DKK.flagURL))
        }
        if(currencies.rates.GBP != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.GBP.title,CurrencyMapper.GBP.subtitle,currencies.rates.GBP,CurrencyMapper.GBP.flagURL))
        }
        if(currencies.rates.HKD != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.HKD.title,CurrencyMapper.HKD.subtitle,currencies.rates.HKD,CurrencyMapper.HKD.flagURL))
        }
        if(currencies.rates.HRK != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.HRK.title,CurrencyMapper.HRK.subtitle,currencies.rates.HRK,CurrencyMapper.HRK.flagURL))
        }
        if(currencies.rates.HUF != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.HUF.title,CurrencyMapper.HUF.subtitle,currencies.rates.HUF,CurrencyMapper.HUF.flagURL))
        }
        if(currencies.rates.IDR != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.IDR.title,CurrencyMapper.IDR.subtitle,currencies.rates.IDR,CurrencyMapper.IDR.flagURL))
        }
        if(currencies.rates.ILS != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.ILS.title,CurrencyMapper.ILS.subtitle,currencies.rates.ILS,CurrencyMapper.ILS.flagURL))
        }
        if(currencies.rates.INR != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.INR.title,CurrencyMapper.INR.subtitle,currencies.rates.INR,CurrencyMapper.INR.flagURL))
        }
        if(currencies.rates.ISK != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.ISK.title,CurrencyMapper.ISK.subtitle,currencies.rates.ISK,CurrencyMapper.ISK.flagURL))
        }
        if(currencies.rates.JPY != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.JPY.title,CurrencyMapper.JPY.subtitle,currencies.rates.JPY,CurrencyMapper.JPY.flagURL))
        }
        if(currencies.rates.KRW != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.KRW.title,CurrencyMapper.KRW.subtitle,currencies.rates.KRW,CurrencyMapper.KRW.flagURL))
        }
        if(currencies.rates.MXN != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.MXN.title,CurrencyMapper.MXN.subtitle,currencies.rates.MXN,CurrencyMapper.MXN.flagURL))
        }
        if(currencies.rates.MYR != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.MYR.title,CurrencyMapper.MYR.subtitle,currencies.rates.MYR,CurrencyMapper.MYR.flagURL))
        }
        if(currencies.rates.NOK != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.NOK.title,CurrencyMapper.NOK.subtitle,currencies.rates.NOK,CurrencyMapper.NOK.flagURL))
        }
        if(currencies.rates.NZD != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.NZD.title,CurrencyMapper.NZD.subtitle,currencies.rates.NZD,CurrencyMapper.NZD.flagURL))
        }
        if(currencies.rates.PHP != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.PHP.title,CurrencyMapper.PHP.subtitle,currencies.rates.PHP,CurrencyMapper.PHP.flagURL))
        }
        if(currencies.rates.PLN != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.PLN.title,CurrencyMapper.PLN.subtitle,currencies.rates.PLN,CurrencyMapper.PLN.flagURL))
        }
        if(currencies.rates.RON != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.RON.title,CurrencyMapper.RON.subtitle,currencies.rates.RON,CurrencyMapper.RON.flagURL))
        }
        if(currencies.rates.RUB != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.RUB.title,CurrencyMapper.RUB.subtitle,currencies.rates.RUB,CurrencyMapper.RON.flagURL))
        }
        if(currencies.rates.SEK != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.SEK.title,CurrencyMapper.SEK.subtitle,currencies.rates.SEK,CurrencyMapper.SEK.flagURL))
        }
        if(currencies.rates.SGD != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.SGD.title,CurrencyMapper.SGD.subtitle,currencies.rates.SGD,CurrencyMapper.SGD.flagURL))
        }
        if(currencies.rates.THB != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.THB.title,CurrencyMapper.THB.subtitle,currencies.rates.THB,CurrencyMapper.THB.flagURL))
        }
        if(currencies.rates.TRY != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.TRY.title,CurrencyMapper.TRY.subtitle,currencies.rates.TRY,CurrencyMapper.TRY.flagURL))
        }
        if(currencies.rates.USD != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.USD.title,CurrencyMapper.USD.subtitle,currencies.rates.USD,CurrencyMapper.USD.flagURL))
        }
        if(currencies.rates.ZAR != 0.00)
        {
            list.add(CurrencyRow(CurrencyMapper.ZAR.title,CurrencyMapper.ZAR.subtitle,currencies.rates.ZAR,CurrencyMapper.ZAR.flagURL))
        }

        return list.toList()
    }

}