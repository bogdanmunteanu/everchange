package ro.bogdanmunteanu.currencyconverter.data.model

data class CurrencyRow(override var isoCode:String, override var name: String, override var rate:Double, override var flagUrl:String) : CurrencyRate()