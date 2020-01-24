package ro.bogdanmunteanu.currencyconverter.ui.adapters

import android.view.View
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.data.model.BaseCurrency
import ro.bogdanmunteanu.currencyconverter.data.model.Currency
import ro.bogdanmunteanu.currencyconverter.ui.holders.AbstractCurrencyViewHolder
import ro.bogdanmunteanu.currencyconverter.ui.holders.BaseCurrencyViewHolder
import ro.bogdanmunteanu.currencyconverter.ui.holders.CurrencyViewHolder
import java.lang.IllegalArgumentException

class CurrencyTypeFactoryImpl : CurrencyTypeFactory {

    override fun type(baseCurrecy: BaseCurrency): Int = R.layout.base_currency_item

    override fun type(currency: Currency): Int = R.layout.currency_item

    override fun holder(type: Int, view: View): AbstractCurrencyViewHolder<*> {
        return when(type){
            R.layout.base_currency_item-> BaseCurrencyViewHolder(view)
            R.layout.currency_item-> CurrencyViewHolder(view)
            else-> throw IllegalArgumentException("Illegal view type")
        }
    }
}