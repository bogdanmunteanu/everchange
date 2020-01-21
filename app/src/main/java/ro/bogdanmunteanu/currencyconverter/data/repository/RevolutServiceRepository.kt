package ro.bogdanmunteanu.currencyconverter.data.repository

import io.reactivex.Single
import ro.bogdanmunteanu.currencyconverter.data.di.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.model.*
import ro.bogdanmunteanu.currencyconverter.ui.CurrencyMapper
import java.math.BigDecimal
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
        list.add(BaseCurrencyRow(baseCurrency.title,baseCurrency.subtitle, BigDecimal(1.00),baseCurrency.flagURL))
        if(BigDecimal(currencies.rates.EUR) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.EUR.title,CurrencyMapper.EUR.subtitle,BigDecimal(currencies.rates.EUR),CurrencyMapper.EUR.flagURL))
        }
        if(BigDecimal(currencies.rates.AUD) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.AUD.title,CurrencyMapper.AUD.subtitle,BigDecimal(currencies.rates.AUD),CurrencyMapper.AUD.flagURL))
        }
        if(BigDecimal(currencies.rates.BGN) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.BGN.title,CurrencyMapper.BGN.subtitle,BigDecimal(currencies.rates.BGN),CurrencyMapper.BGN.flagURL))
        }
        if(BigDecimal(currencies.rates.BRL) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.BRL.title,CurrencyMapper.BRL.subtitle,BigDecimal(currencies.rates.BRL),CurrencyMapper.BRL.flagURL))
        }
        if(BigDecimal(currencies.rates.CAD) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.CAD.title,CurrencyMapper.CAD.subtitle,BigDecimal(currencies.rates.CAD),CurrencyMapper.CAD.flagURL))
        }
        if(BigDecimal(currencies.rates.CHF) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.CHF.title,CurrencyMapper.CHF.subtitle,BigDecimal(currencies.rates.CHF),CurrencyMapper.CHF.flagURL))
        }
        if(BigDecimal(currencies.rates.CNY) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.CNY.title,CurrencyMapper.CNY.subtitle,BigDecimal(currencies.rates.CNY),CurrencyMapper.CNY.flagURL))
        }
        if(BigDecimal(currencies.rates.CZK) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.CZK.title,CurrencyMapper.CZK.subtitle,BigDecimal(currencies.rates.CZK),CurrencyMapper.CZK.flagURL))
        }
        if(BigDecimal(currencies.rates.DKK) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.DKK.title,CurrencyMapper.DKK.subtitle,BigDecimal(currencies.rates.DKK),CurrencyMapper.DKK.flagURL))
        }
        if(BigDecimal(currencies.rates.GBP) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.GBP.title,CurrencyMapper.GBP.subtitle,BigDecimal(currencies.rates.GBP),CurrencyMapper.GBP.flagURL))
        }
        if(BigDecimal(currencies.rates.HKD) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.HKD.title,CurrencyMapper.HKD.subtitle,BigDecimal(currencies.rates.HKD),CurrencyMapper.HKD.flagURL))
        }
        if(BigDecimal(currencies.rates.HRK) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.HRK.title,CurrencyMapper.HRK.subtitle,BigDecimal(currencies.rates.HRK),CurrencyMapper.HRK.flagURL))
        }
        if(BigDecimal(currencies.rates.HUF) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.HUF.title,CurrencyMapper.HUF.subtitle,BigDecimal(currencies.rates.HUF),CurrencyMapper.HUF.flagURL))
        }
        if(BigDecimal(currencies.rates.IDR) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.IDR.title,CurrencyMapper.IDR.subtitle,BigDecimal(currencies.rates.IDR),CurrencyMapper.IDR.flagURL))
        }
        if(BigDecimal(currencies.rates.ILS) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.ILS.title,CurrencyMapper.ILS.subtitle,BigDecimal(currencies.rates.ILS),CurrencyMapper.ILS.flagURL))
        }
        if(BigDecimal(currencies.rates.INR) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.INR.title,CurrencyMapper.INR.subtitle,BigDecimal(currencies.rates.INR),CurrencyMapper.INR.flagURL))
        }
        if(BigDecimal(currencies.rates.ISK) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.ISK.title,CurrencyMapper.ISK.subtitle,BigDecimal(currencies.rates.ISK),CurrencyMapper.ISK.flagURL))
        }
        if(BigDecimal(currencies.rates.JPY) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.JPY.title,CurrencyMapper.JPY.subtitle,BigDecimal(currencies.rates.JPY),CurrencyMapper.JPY.flagURL))
        }
        if(BigDecimal(currencies.rates.KRW) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.KRW.title,CurrencyMapper.KRW.subtitle,BigDecimal(currencies.rates.KRW),CurrencyMapper.KRW.flagURL))
        }
        if(BigDecimal(currencies.rates.MXN) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.MXN.title,CurrencyMapper.MXN.subtitle,BigDecimal(currencies.rates.MXN),CurrencyMapper.MXN.flagURL))
        }
        if(BigDecimal(currencies.rates.MYR) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.MYR.title,CurrencyMapper.MYR.subtitle,BigDecimal(currencies.rates.MYR),CurrencyMapper.MYR.flagURL))
        }
        if(BigDecimal(currencies.rates.NOK) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.NOK.title,CurrencyMapper.NOK.subtitle,BigDecimal(currencies.rates.NOK),CurrencyMapper.NOK.flagURL))
        }
        if(BigDecimal(currencies.rates.NZD) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.NZD.title,CurrencyMapper.NZD.subtitle,BigDecimal(currencies.rates.NZD),CurrencyMapper.NZD.flagURL))
        }
        if(BigDecimal(currencies.rates.PHP) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.PHP.title,CurrencyMapper.PHP.subtitle,BigDecimal(currencies.rates.PHP),CurrencyMapper.PHP.flagURL))
        }
        if(BigDecimal(currencies.rates.PLN) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.PLN.title,CurrencyMapper.PLN.subtitle,BigDecimal(currencies.rates.PLN),CurrencyMapper.PLN.flagURL))
        }
        if(BigDecimal(currencies.rates.RON) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.RON.title,CurrencyMapper.RON.subtitle,BigDecimal(currencies.rates.RON),CurrencyMapper.RON.flagURL))
        }
        if(BigDecimal(currencies.rates.RUB) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.RUB.title,CurrencyMapper.RUB.subtitle,BigDecimal(currencies.rates.RUB),CurrencyMapper.RON.flagURL))
        }
        if(BigDecimal(currencies.rates.SEK) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.SEK.title,CurrencyMapper.SEK.subtitle,BigDecimal(currencies.rates.SEK),CurrencyMapper.SEK.flagURL))
        }
        if(BigDecimal(currencies.rates.SGD) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.SGD.title,CurrencyMapper.SGD.subtitle,BigDecimal(currencies.rates.SGD),CurrencyMapper.SGD.flagURL))
        }
        if(BigDecimal(currencies.rates.THB) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.THB.title,CurrencyMapper.THB.subtitle,BigDecimal(currencies.rates.THB),CurrencyMapper.THB.flagURL))
        }
        if(BigDecimal(currencies.rates.TRY) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.TRY.title,CurrencyMapper.TRY.subtitle,BigDecimal(currencies.rates.TRY),CurrencyMapper.TRY.flagURL))
        }
        if(BigDecimal(currencies.rates.USD) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.USD.title,CurrencyMapper.USD.subtitle,BigDecimal(currencies.rates.USD),CurrencyMapper.USD.flagURL))
        }
        if(BigDecimal(currencies.rates.ZAR) != BigDecimal.ZERO)
        {
            list.add(CurrencyRow(CurrencyMapper.ZAR.title,CurrencyMapper.ZAR.subtitle,BigDecimal(currencies.rates.ZAR),CurrencyMapper.ZAR.flagURL))
        }

        return list.toList()
    }

}