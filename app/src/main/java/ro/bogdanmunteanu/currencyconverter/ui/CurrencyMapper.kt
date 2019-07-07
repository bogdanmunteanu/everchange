package ro.bogdanmunteanu.currencyconverter.ui

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import ro.bogdanmunteanu.currencyconverter.R

enum class CurrencyMapper(val title: String, val subtitle: String, val flagURL: String) {
    EUR("EUR", "Euro", "https://www.countryflags.io/eu/flat/64.png"),
    AUD("AUD", "Australian dollar", "https://www.countryflags.io/au/flat/64.png"),
    BGN("BGN", "Bulgarian lev", "https://www.countryflags.io/bg/flat/64.png"),
    BRL("BRL", "Brazilian real", "https://www.countryflags.io/br/flat/64.png"),
    CAD("CAD", "Canadian dollar", "https://www.countryflags.io/ca/flat/64.png"),
    CHF("CHF", "Swiss franc", "https://www.countryflags.io/ch/flat/64.png"),
    CNY("CNY", "Chinese yuan", "https://www.countryflags.io/cn/flat/64.png"),
    CZK("CZK", "Czech koruna", "https://www.countryflags.io/cz/flat/64.png"),
    DKK("DKK", "Danish krone", "https://www.countryflags.io/dk/flat/64.png"),
    GBP("GBP", "British pound", "https://www.countryflags.io/gb/flat/64.png"),
    HKD("HKD", "Hong Kong dollar", "https://www.countryflags.io/hk/flat/64.png"),
    HRK("HRK", "Croatian kuna", "https://www.countryflags.io/hr/flat/64.png"),
    HUF("HUF", "Hungarian forint", "https://www.countryflags.io/hu/flat/64.png"),
    IDR("IDR", "Indonesian rupiah", "https://www.countryflags.io/id/flat/64.png"),
    ILS("ILS", "Israeli new shekel", "https://www.countryflags.io/il/flat/64.png"),
    INR("INR", "Indian rupee", "https://www.countryflags.io/in/flat/64.png"),
    ISK("ISK", "Icelandic krona", "https://www.countryflags.io/is/flat/64.png"),
    JPY("JPY", "Japanese yen", "https://www.countryflags.io/jp/flat/64.png"),
    KRW("KRW", "South Korean won", "https://www.countryflags.io/kr/flat/64.png"),
    MXN("MXN", "Mexican peso", "https://www.countryflags.io/mx/flat/64.png"),
    MYR("MYR", "Malaysian ringgit", "https://www.countryflags.io/my/flat/64.png"),
    NOK("NOK", "Norwegian krone", "https://www.countryflags.io/no/flat/64.png"),
    NZD("NZD", "New Zeeland dollar", "https://www.countryflags.io/nz/flat/64.png"),
    PHP("PHP", "Phillippine peso", "https://www.countryflags.io/ph/flat/64.png"),
    PLN("PLN", "Polish zloty", "https://www.countryflags.io/pl/flat/64.png"),
    RON("RON", "Romanian leu", "https://www.countryflags.io/ro/flat/64.png"),
    RUB("RUB", "Russian ruble", "https://www.countryflags.io/ru/flat/64.png"),
    SEK("SEK", "Swedish krona", "https://www.countryflags.io/se/flat/64.png"),
    SGD("SGD", "Singapore dollar", "https://www.countryflags.io/sg/flat/64.png"),
    THB("THB", "Thai baht", "https://www.countryflags.io/th/flat/64.png"),
    TRY("TRY", "Turkish lira", "https://www.countryflags.io/tr/flat/64.png"),
    USD("USD", "U.S. Dollar", "https://www.countryflags.io/us/flat/64.png"),
    ZAR("ZAR", "South African rand", "https://www.countryflags.io/za/flat/64.png");

    companion object {
        fun fromTitle(title: String): CurrencyMapper = values().find { value -> value.title == title } ?: EUR


    }


}