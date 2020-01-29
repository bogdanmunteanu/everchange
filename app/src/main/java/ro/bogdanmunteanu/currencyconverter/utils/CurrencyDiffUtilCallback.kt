package ro.bogdanmunteanu.currencyconverter.utils

import androidx.recyclerview.widget.DiffUtil
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyRate
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.BaseCurrencyModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel

class CurrencyDiffUtilCallback(
    var oldRates: List<CurrencyAbstractModel>?,
    var newRates: List<CurrencyAbstractModel>?
) :
    DiffUtil.Callback() {

    companion object {
        const val RATE = "rate"
        const val CURRENCY = "currency"
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val new = newRates?.let { it[0] }
        val old = oldRates?.let { it[0] }

        return (new is BaseCurrencyModel && old is BaseCurrencyModel)
    }

    override fun getOldListSize(): Int {
        return oldRates!!.size
    }

    override fun getNewListSize(): Int {
        return newRates!!.size
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRates!![oldItemPosition] == newRates!![newItemPosition]
    }
}