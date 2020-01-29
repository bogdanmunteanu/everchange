package ro.bogdanmunteanu.currencyconverter.ui.adapters

import android.view.View
import ro.bogdanmunteanu.currencyconverter.data.model.BaseCurrency
import ro.bogdanmunteanu.currencyconverter.data.model.Currency
import ro.bogdanmunteanu.currencyconverter.ui.holders.AbstractCurrencyViewHolder

interface CurrencyTypeFactory {
    fun type(baseCurrecy: BaseCurrency): Int
    fun type(currency: Currency): Int

    fun holder(type: Int, view: View): AbstractCurrencyViewHolder<*>
}