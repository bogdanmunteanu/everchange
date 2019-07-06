package ro.bogdanmunteanu.currencyconverter.data.model

abstract class CurrencyRate {
    abstract val isoCode:String
    abstract val name: String
    abstract val rate:Double
    abstract val flagUrl:String
}