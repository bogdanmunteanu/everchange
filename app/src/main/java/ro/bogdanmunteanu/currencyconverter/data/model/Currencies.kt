package ro.bogdanmunteanu.currencyconverter.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Currencies constructor(
    val base: String,
    val date: String,
    val rates: CurrencyRates
) : Parcelable