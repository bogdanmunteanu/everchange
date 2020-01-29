package ro.bogdanmunteanu.currencyconverter.data.model.bindings

import ro.bogdanmunteanu.currencyconverter.ui.adapters.CurrencyTypeFactory

abstract class CurrencyAbstractModel {
    abstract fun type(typeFactory: CurrencyTypeFactory): Int
}