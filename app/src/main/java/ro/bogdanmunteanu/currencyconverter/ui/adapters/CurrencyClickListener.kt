package ro.bogdanmunteanu.currencyconverter.ui.adapters

import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel

interface CurrencyClickListener {
    fun onItemClick(position : Int,item: CurrencyAbstractModel)

}