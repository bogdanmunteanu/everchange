package ro.bogdanmunteanu.currencyconverter.data.model

import java.math.BigDecimal

data class BaseCurrencyRow(override val isoCode:String, override val name: String, override val rate:BigDecimal, override val flagUrl:String) : CurrencyRate()