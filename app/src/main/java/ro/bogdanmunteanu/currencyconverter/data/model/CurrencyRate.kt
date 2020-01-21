package ro.bogdanmunteanu.currencyconverter.data.model

import java.math.BigDecimal

abstract class CurrencyRate {
    abstract val isoCode:String
    abstract val name: String
    abstract val rate: BigDecimal
    abstract val flagUrl:String
}