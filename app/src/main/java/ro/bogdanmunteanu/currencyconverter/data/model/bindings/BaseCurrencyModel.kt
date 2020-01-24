package ro.bogdanmunteanu.currencyconverter.data.model.bindings

import ro.bogdanmunteanu.currencyconverter.data.model.BaseCurrency
import ro.bogdanmunteanu.currencyconverter.ui.adapters.CurrencyTypeFactory

class BaseCurrencyModel (val baseCurrency: BaseCurrency) : CurrencyAbstractModel(){
    override fun type(typeFactory: CurrencyTypeFactory): Int {
        return typeFactory.type(baseCurrency)
    }
}