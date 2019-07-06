package ro.bogdanmunteanu.currencyconverter.data.model

data class CurrencyRate constructor(val baseCurrency:String,val isoCode:String,val name: String, val rate:Double,val flagUrl:String)