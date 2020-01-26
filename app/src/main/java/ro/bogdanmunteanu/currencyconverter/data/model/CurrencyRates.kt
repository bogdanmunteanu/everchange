package ro.bogdanmunteanu.currencyconverter.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CurrencyRates constructor(
    val EUR:Double,
    val AUD:Double,
    val BGN:Double,
    val BRL:Double,
    val CAD:Double,
    val CHF:Double,
    val CNY:Double,
    val CZK:Double,
    val DKK:Double,
    val GBP:Double,
    val HKD:Double,
    val HRK:Double,
    val HUF:Double,
    val IDR:Double,
    val ILS:Double,
    val INR:Double,
    val ISK:Double,
    val JPY:Double,
    val KRW:Double,
    val MXN:Double,
    val MYR:Double,
    val NOK:Double,
    val NZD:Double,
    val PHP:Double,
    val PLN:Double,
    val RON:Double,
    val RUB:Double,
    val SEK:Double,
    val SGD:Double,
    val THB:Double,
    val TRY:Double,
    val USD:Double,
    val ZAR:Double) :Parcelable

//{"base":"EUR","date":"2018-09-06","rates":{"AUD":1.6214,"BGN":1.9618,"BRL":4.8066,"CAD":1.5385,"CHF":1.131,"CNY":7.9696,"CZK":25.794,"DKK":7.4797,"GBP":0.90101,"HKD":9.1606,"HRK":7.457,"HUF":327.5,"IDR":17377.0,"ILS":4.1835,"INR":83.976,"ISK":128.19,"JPY":129.95,"KRW":1308.8,"MXN":22.434,"MYR":4.8268,"NOK":9.8062,"NZD":1.7687,"PHP":62.785,"PLN":4.3316,"RON":4.6528,"RUB":79.82,"SEK":10.623,"SGD":1.6049,"THB":38.248,"TRY":7.6517,"USD":1.167,"ZAR":17.878}}