package ro.bogdanmunteanu.currencyconverter.data.model.bindings

import ro.bogdanmunteanu.currencyconverter.data.model.BaseCurrency
import ro.bogdanmunteanu.currencyconverter.data.model.Currency
import ro.bogdanmunteanu.currencyconverter.ui.adapters.CurrencyTypeFactory

class BaseCurrencyModel(var baseCurrency: BaseCurrency) : CurrencyAbstractModel() {
    override fun type(typeFactory: CurrencyTypeFactory): Int {
        return typeFactory.type(baseCurrency)
    }

    override fun toString(): String {
        return "BaseCurrencyModel(baseCurrency=$baseCurrency)"
    }


}