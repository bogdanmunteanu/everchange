package ro.bogdanmunteanu.currencyconverter.data.model

data class BaseCurrencyRow(override val isoCode:String, override val name: String, override val rate:Double, override val flagUrl:String) : CurrencyRate()