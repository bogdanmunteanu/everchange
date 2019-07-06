package ro.bogdanmunteanu.currencyconverter.ui

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import ro.bogdanmunteanu.currencyconverter.R

enum class CurrencyMapper(val title:String,val subtitle:String, val flagURL:String)
{
//    EUR("EUR","Euro", R.mipmap.eur),
//    AUD("AUD","Australian dollar",R.mipmap.aud),
//    BGN("BGN","Bulgarian lev",R.mipmap.bgn),
//    BRL("BRL","Brazilian real",R.mipmap.brl),
//    CAD("CAD","Canadian dollar",R.mipmap.cad),
//    CHF("CHF","Swiss franc",R.mipmap.chf),
//    CNY("CNY","Chinese yuan",R.mipmap.cny),
//    CZK("CZK","Czech koruna",R.mipmap.czk),
//    DKK("DKK","Danish krone",R.mipmap.dkk),
//    GBP("GBP","British pound",R.mipmap.gbp),
//    HKD("HKD","Hong Kong dollar",R.mipmap.hkd),
//    HRK("HRK","Croatian kuna",R.mipmap.hrk),
//    HUF("HUF","Hungarian forint",R.mipmap.huf),
//    IDR("IDR","Indonesian rupiah",R.mipmap.idr),
//    ILS("ILS","Israeli new shekel",R.mipmap.ils),
//    INR("INR","Indian rupee",R.mipmap.inr),
//    ISK("ISK","Icelandic krona",R.mipmap.isk),
//    JPY("JPY","Japanese yen",R.mipmap.jpy),
//    KRW("KRW","South Korean won",R.mipmap.krw),
//    MXN("MXN","Mexican peso",R.mipmap.mxn),
//    MYR("MYR","Malaysian ringgit",R.mipmap.myr),
//    NOK("NOK","Norwegian krone",R.mipmap.nok),
//    NZD("NZD","New Zeeland dollar",R.mipmap.nzd),
//    PHP("PHP","Phillippine peso",R.mipmap.php),
//    PLN("PLN","Polish zloty",R.mipmap.pln),
//    RON("RON","Romanian leu",R.mipmap.ron),
//    RUB("RUB","Russian ruble",R.mipmap.rub),
//    SEK("SEK","Swedish krona",R.mipmap.sek),
//    SGD("SGD","Singapore dollar",R.mipmap.sgd),
//    THB("THB","Thai baht",R.mipmap.thb),
//    TRY("TRY","Turkish lira",R.mipmap.trl),
//    USD("USD","U.S. Dollar",R.mipmap.usd),
//    ZAR("ZAR","South African rand",R.mipmap.zar);

//     EUR("EUR","Euro", "R.mipmap.eur"),
//        AUD("AUD","Australian dollar","R.mipmap.aud"),
//        BGN("BGN","Bulgarian lev","R.mipmap.bgn"),
//        BRL("BRL","Brazilian real","R.mipmap.brl"),
//        CAD("CAD","Canadian dollar","R.mipmap.cad"),
//        CHF("CHF","Swiss franc","R.mipmap.chf"),
//        CNY("CNY","Chinese yuan","R.mipmap.cny"),
//        CZK("CZK","Czech koruna","R.mipmap.czk"),
//        DKK("DKK","Danish krone","R.mipmap.dkk"),
//        GBP("GBP","British pound","R.mipmap.gbp"),
//        HKD("HKD","Hong Kong dollar","R.mipmap.hkd"),
//        HRK("HRK","Croatian kuna","R.mipmap.hrk"),
//        HUF("HUF","Hungarian forint","R.mipmap.huf"),
//        IDR("IDR","Indonesian rupiah","R.mipmap.idr"),
//        ILS("ILS","Israeli new shekel","R.mipmap.ils"),
//        INR("INR","Indian rupee","R.mipmap.inr"),
//        ISK("ISK","Icelandic krona","R.mipmap.isk"),
//        JPY("JPY","Japanese yen","R.mipmap.jpy"),
//        KRW("KRW","South Korean won","R.mipmap.krw"),
//        MXN("MXN","Mexican peso","R.mipmap.mxn"),
//        MYR("MYR","Malaysian ringgit","R.mipmap.myr"),
//        NOK("NOK","Norwegian krone","R.mipmap.nok"),
//        NZD("NZD","New Zeeland dollar","R.mipmap.nzd"),
//        PHP("PHP","Phillippine peso","R.mipmap.php"),
//        PLN("PLN","Polish zloty","R.mipmap.pln"),
//        RON("RON","Romanian leu","R.mipmap.ron"),
//        RUB("RUB","Russian ruble","R.mipmap.rub"),
//        SEK("SEK","Swedish krona","R.mipmap.sek"),
//        SGD("SGD","Singapore dollar","R.mipmap.sgd"),
//        THB("THB","Thai baht","R.mipmap.thb"),
//        TRY("TRY","Turkish lira","R.mipmap.trl"),
//        USD("USD","U.S. Dollar","R.mipmap.usd"),
//        ZAR("ZAR","South African rand","R.mipmap.zar");


        EUR("EUR","Euro", "eur"),
        AUD("AUD","Australian dollar","aud"),
        BGN("BGN","Bulgarian lev","bgn"),
        BRL("BRL","Brazilian real","brl"),
        CAD("CAD","Canadian dollar","cad"),
        CHF("CHF","Swiss franc","chf"),
        CNY("CNY","Chinese yuan","cny"),
        CZK("CZK","Czech koruna","czk"),
        DKK("DKK","Danish krone","dkk"),
        GBP("GBP","British pound","gbp"),
        HKD("HKD","Hong Kong dollar","hkd"),
        HRK("HRK","Croatian kuna","hrk"),
        HUF("HUF","Hungarian forint","huf"),
        IDR("IDR","Indonesian rupiah","idr"),
        ILS("ILS","Israeli new shekel","ils"),
        INR("INR","Indian rupee","inr"),
        ISK("ISK","Icelandic krona","isk"),
        JPY("JPY","Japanese yen","jpy"),
        KRW("KRW","South Korean won","krw"),
        MXN("MXN","Mexican peso","mxn"),
        MYR("MYR","Malaysian ringgit","myr"),
        NOK("NOK","Norwegian krone","nok"),
        NZD("NZD","New Zeeland dollar","nzd"),
        PHP("PHP","Phillippine peso","php"),
        PLN("PLN","Polish zloty","pln"),
        RON("RON","Romanian leu","ron"),
        RUB("RUB","Russian ruble","rub"),
        SEK("SEK","Swedish krona","sek"),
        SGD("SGD","Singapore dollar","sgd"),
        THB("THB","Thai baht","thb"),
        TRY("TRY","Turkish lira","trl"),
        USD("USD","U.S. Dollar","usd"),
        ZAR("ZAR","South African rand","zar");

    companion object {
        fun fromTitle(title:String): CurrencyMapper = values().find { value -> value.title==title } ?: EUR
    }












}