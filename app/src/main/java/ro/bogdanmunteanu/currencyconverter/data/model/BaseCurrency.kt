package ro.bogdanmunteanu.currencyconverter.data.model

import java.math.BigDecimal

data class BaseCurrency(
    val isoCode: String,
    val name: String,
    val rate: BigDecimal,
    val flagUrl: String
)