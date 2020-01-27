package ro.bogdanmunteanu.currencyconverter.data.repository

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ro.bogdanmunteanu.currencyconverter.data.di.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.model.*
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.BaseCurrencyModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyModel
import ro.bogdanmunteanu.currencyconverter.persistence.CurrencyRepository
import ro.bogdanmunteanu.currencyconverter.ui.CurrencyMapper
import java.lang.NumberFormatException
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.collections.ArrayList

class RevolutServiceRepository @Inject constructor(private val apiService: RevolutApiService,private val cache: CurrencyRepository){

    fun getCurrenciesFromEndpoint(baseCurrency:String,input:String = BigDecimal.ONE.toString()) : Flowable<List<CurrencyAbstractModel>> {
        return apiService.getCurrencies(baseCurrency).map { t: Currencies -> mapCurrencies(t,input) }
    }

    private fun mapCurrencies(currencies: Currencies,input: String) : List<CurrencyAbstractModel>
    {
        var baseCurrency: CurrencyMapper = CurrencyMapper.fromTitle(currencies.base)
        val list = ArrayList<CurrencyAbstractModel>()
        list.clear()
        try {
            list.add(BaseCurrencyModel(BaseCurrency(baseCurrency.title,baseCurrency.subtitle, BigDecimal(input),baseCurrency.flagURL)))
        }catch( e: NumberFormatException){
            list.add(BaseCurrencyModel(BaseCurrency(baseCurrency.title,baseCurrency.subtitle, BigDecimal.ONE,baseCurrency.flagURL)))
        }
        if(BigDecimal(currencies.rates.EUR) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.EUR.title,CurrencyMapper.EUR.subtitle,BigDecimal(currencies.rates.EUR),CurrencyMapper.EUR.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.AUD) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.AUD.title,CurrencyMapper.AUD.subtitle,BigDecimal(currencies.rates.AUD),CurrencyMapper.AUD.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.BGN) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.BGN.title,CurrencyMapper.BGN.subtitle,BigDecimal(currencies.rates.BGN),CurrencyMapper.BGN.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.BRL) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.BRL.title,CurrencyMapper.BRL.subtitle,BigDecimal(currencies.rates.BRL),CurrencyMapper.BRL.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.CAD) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.CAD.title,CurrencyMapper.CAD.subtitle,BigDecimal(currencies.rates.CAD),CurrencyMapper.CAD.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.CHF) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.CHF.title,CurrencyMapper.CHF.subtitle,BigDecimal(currencies.rates.CHF),CurrencyMapper.CHF.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.CNY) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.CNY.title,CurrencyMapper.CNY.subtitle,BigDecimal(currencies.rates.CNY),CurrencyMapper.CNY.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.CZK) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.CZK.title,CurrencyMapper.CZK.subtitle,BigDecimal(currencies.rates.CZK),CurrencyMapper.CZK.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.DKK) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.DKK.title,CurrencyMapper.DKK.subtitle,BigDecimal(currencies.rates.DKK),CurrencyMapper.DKK.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.GBP) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.GBP.title,CurrencyMapper.GBP.subtitle,BigDecimal(currencies.rates.GBP),CurrencyMapper.GBP.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.HKD) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.HKD.title,CurrencyMapper.HKD.subtitle,BigDecimal(currencies.rates.HKD),CurrencyMapper.HKD.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.HRK) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.HRK.title,CurrencyMapper.HRK.subtitle,BigDecimal(currencies.rates.HRK),CurrencyMapper.HRK.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.HUF) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.HUF.title,CurrencyMapper.HUF.subtitle,BigDecimal(currencies.rates.HUF),CurrencyMapper.HUF.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.IDR) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.IDR.title,CurrencyMapper.IDR.subtitle,BigDecimal(currencies.rates.IDR),CurrencyMapper.IDR.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.ILS) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.ILS.title,CurrencyMapper.ILS.subtitle,BigDecimal(currencies.rates.CNY),CurrencyMapper.CNY.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.INR) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.INR.title,CurrencyMapper.INR.subtitle,BigDecimal(currencies.rates.INR),CurrencyMapper.INR.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.ISK) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.ISK.title,CurrencyMapper.ISK.subtitle,BigDecimal(currencies.rates.ISK),CurrencyMapper.ISK.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.JPY) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.JPY.title,CurrencyMapper.JPY.subtitle,BigDecimal(currencies.rates.JPY),CurrencyMapper.JPY.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.KRW) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.KRW.title,CurrencyMapper.KRW.subtitle,BigDecimal(currencies.rates.KRW),CurrencyMapper.KRW.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.MXN) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.MXN.title,CurrencyMapper.MXN.subtitle,BigDecimal(currencies.rates.MXN),CurrencyMapper.MXN.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.MYR) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.MYR.title,CurrencyMapper.MYR.subtitle,BigDecimal(currencies.rates.MYR),CurrencyMapper.MYR.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.NOK) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.NOK.title,CurrencyMapper.NOK.subtitle,BigDecimal(currencies.rates.NOK),CurrencyMapper.NOK.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.NZD) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.NZD.title,CurrencyMapper.NZD.subtitle,BigDecimal(currencies.rates.NZD),CurrencyMapper.NZD.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.PHP) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.PHP.title,CurrencyMapper.PHP.subtitle,BigDecimal(currencies.rates.PHP),CurrencyMapper.PHP.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.PLN) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.PLN.title,CurrencyMapper.PLN.subtitle,BigDecimal(currencies.rates.PLN),CurrencyMapper.PLN.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.RON) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.RON.title,CurrencyMapper.RON.subtitle,BigDecimal(currencies.rates.RON),CurrencyMapper.RON.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.RUB) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.RUB.title,CurrencyMapper.RUB.subtitle,BigDecimal(currencies.rates.RUB),CurrencyMapper.RUB.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.SEK) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.SEK.title,CurrencyMapper.SEK.subtitle,BigDecimal(currencies.rates.SEK),CurrencyMapper.SEK.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.SGD) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.SGD.title,CurrencyMapper.SGD.subtitle,BigDecimal(currencies.rates.SGD),CurrencyMapper.SGD.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.THB) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.THB.title,CurrencyMapper.THB.subtitle,BigDecimal(currencies.rates.THB),CurrencyMapper.THB.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.TRY) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.TRY.title,CurrencyMapper.TRY.subtitle,BigDecimal(currencies.rates.TRY),CurrencyMapper.TRY.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.USD) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.USD.title,CurrencyMapper.USD.subtitle,BigDecimal(currencies.rates.USD),CurrencyMapper.USD.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }
        if(BigDecimal(currencies.rates.ZAR) != BigDecimal.ZERO)
        {
            val currency = Currency(CurrencyMapper.ZAR.title,CurrencyMapper.ZAR.subtitle,BigDecimal(currencies.rates.ZAR),CurrencyMapper.ZAR.flagURL)
            if(cache.getCurrency(currency)!=null)
            {
                cache.update(currency)
            }else{
                cache.insert(currency)
            }
            list.add(CurrencyModel(currency))
        }

        return list.toList()
    }







}