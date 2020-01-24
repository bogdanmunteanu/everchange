package ro.bogdanmunteanu.currencyconverter.data.model

import java.math.BigDecimal

data class Currency(
    var isoCode: String,
    var name: String,
    var rate: BigDecimal,
    var flagUrl: String
)