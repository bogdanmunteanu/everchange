package ro.bogdanmunteanu.currencyconverter.utils

import androidx.recyclerview.widget.DiffUtil
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyRate

class CurrencyDiffUtilCallback(var oldRates: List<CurrencyRate>?, var newRates: List<CurrencyRate>?) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRates!![oldItemPosition].rate == newRates!![newItemPosition].rate
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
       return oldRates!![oldItemPosition].equals(newRates!![newItemPosition])
    }
}