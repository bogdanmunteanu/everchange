package ro.bogdanmunteanu.currencyconverter.utils

import ro.bogdanmunteanu.currencyconverter.data.model.BaseCurrency
import ro.bogdanmunteanu.currencyconverter.data.model.Currency
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyResponse
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.BaseCurrencyModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyModel
import java.math.BigDecimal

object CurrencyMapper {

     fun mapCurrencies(
        currencyResponse: CurrencyResponse,
        input: String
    ): List<CurrencyAbstractModel> {
        val mapper: MappedCurrency = MappedCurrency.fromTitle(currencyResponse.base)
        val list = ArrayList<CurrencyAbstractModel>()
        try {
            list.add(
                BaseCurrencyModel(
                    BaseCurrency(
                        mapper.title,
                        mapper.subtitle,
                        BigDecimal(input),
                        mapper.flagURL,
                        mapper.priority
                    )
                )
            )
        } catch (e: NumberFormatException) {
            list.add(
                BaseCurrencyModel(
                    BaseCurrency(
                        mapper.title,
                        mapper.subtitle,
                        BigDecimal.ONE,
                        mapper.flagURL,
                        mapper.priority
                    )
                )
            )
        }
        currencyResponse.rates.keys.forEach { key ->
            val mapper: MappedCurrency = MappedCurrency.fromTitle(key)
            val currency = CurrencyModel(
                Currency(
                    mapper.title,
                    mapper.subtitle,
                    BigDecimal(currencyResponse.rates[key]),
                    mapper.flagURL,
                    mapper.priority
                )
            )
            list.add(currency)
        }
        return list.toList()
    }

    enum class MappedCurrency(
        val title: String,
        val subtitle: String,
        val flagURL: String,
        val priority: Int
    ) {
        EUR("EUR", "Euro", "https://www.countryflags.io/eu/flat/64.png", 1),
        AUD("AUD", "Australian dollar", "https://www.countryflags.io/au/flat/64.png", 2),
        BGN("BGN", "Bulgarian lev", "https://www.countryflags.io/bg/flat/64.png", 3),
        BRL("BRL", "Brazilian real", "https://www.countryflags.io/br/flat/64.png", 4),
        CAD("CAD", "Canadian dollar", "https://www.countryflags.io/ca/flat/64.png", 5),
        CHF("CHF", "Swiss franc", "https://www.countryflags.io/ch/flat/64.png", 6),
        CNY("CNY", "Chinese yuan", "https://www.countryflags.io/cn/flat/64.png", 7),
        CZK("CZK", "Czech koruna", "https://www.countryflags.io/cz/flat/64.png", 8),
        DKK("DKK", "Danish krone", "https://www.countryflags.io/dk/flat/64.png", 9),
        GBP("GBP", "British pound", "https://www.countryflags.io/gb/flat/64.png", 10),
        HKD("HKD", "Hong Kong dollar", "https://www.countryflags.io/hk/flat/64.png", 11),
        HRK("HRK", "Croatian kuna", "https://www.countryflags.io/hr/flat/64.png", 12),
        HUF("HUF", "Hungarian forint", "https://www.countryflags.io/hu/flat/64.png", 13),
        IDR("IDR", "Indonesian rupiah", "https://www.countryflags.io/id/flat/64.png", 14),
        ILS("ILS", "Israeli new shekel", "https://www.countryflags.io/il/flat/64.png", 15),
        INR("INR", "Indian rupee", "https://www.countryflags.io/in/flat/64.png", 16),
        ISK("ISK", "Icelandic krona", "https://www.countryflags.io/is/flat/64.png", 17),
        JPY("JPY", "Japanese yen", "https://www.countryflags.io/jp/flat/64.png", 18),
        KRW("KRW", "South Korean won", "https://www.countryflags.io/kr/flat/64.png", 19),
        MXN("MXN", "Mexican peso", "https://www.countryflags.io/mx/flat/64.png", 20),
        MYR("MYR", "Malaysian ringgit", "https://www.countryflags.io/my/flat/64.png", 21),
        NOK("NOK", "Norwegian krone", "https://www.countryflags.io/no/flat/64.png", 22),
        NZD("NZD", "New Zeeland dollar", "https://www.countryflags.io/nz/flat/64.png", 23),
        PHP("PHP", "Phillippine peso", "https://www.countryflags.io/ph/flat/64.png", 24),
        PLN("PLN", "Polish zloty", "https://www.countryflags.io/pl/flat/64.png", 25),
        RON("RON", "Romanian leu", "https://www.countryflags.io/ro/flat/64.png", 26),
        RUB("RUB", "Russian ruble", "https://www.countryflags.io/ru/flat/64.png", 27),
        SEK("SEK", "Swedish krona", "https://www.countryflags.io/se/flat/64.png", 28),
        SGD("SGD", "Singapore dollar", "https://www.countryflags.io/sg/flat/64.png", 29),
        THB("THB", "Thai baht", "https://www.countryflags.io/th/flat/64.png", 30),
        TRY("TRY", "Turkish lira", "https://www.countryflags.io/tr/flat/64.png", 31),
        USD("USD", "U.S. Dollar", "https://www.countryflags.io/us/flat/64.png", 32),
        ZAR("ZAR", "South African rand", "https://www.countryflags.io/za/flat/64.png", 33);

        companion object {
            fun fromTitle(title: String): MappedCurrency =
                values().find { value -> value.title == title } ?: EUR


        }


    }


}