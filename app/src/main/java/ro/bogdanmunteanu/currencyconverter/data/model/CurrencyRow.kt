package ro.bogdanmunteanu.currencyconverter.data.model

import java.math.BigDecimal

data class CurrencyRow(override var isoCode:String, override var name: String, override var rate:BigDecimal, override var flagUrl:String) : CurrencyRate()