package ro.bogdanmunteanu.currencyconverter.data.model.bindings

import ro.bogdanmunteanu.currencyconverter.data.model.Currency
import ro.bogdanmunteanu.currencyconverter.ui.adapters.CurrencyTypeFactory

class CurrencyModel(val currency : Currency) : CurrencyAbstractModel() {
    override fun type(typeFactory: CurrencyTypeFactory): Int {
        return typeFactory.type(currency)
    }

    override fun toString(): String {
        return "CurrencyModel(currency=$currency)"
    }


}