@file:Suppress("UNCHECKED_CAST")

package ro.bogdanmunteanu.currencyconverter.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.bogdanmunteanu.currencyconverter.data.model.BaseCurrency
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.BaseCurrencyModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyModel
import ro.bogdanmunteanu.currencyconverter.ui.holders.AbstractCurrencyViewHolder
import java.math.BigDecimal


class CurrenciesAdapter(
    private val currencies: ArrayList<CurrencyAbstractModel>,
    private val typeFactory: CurrencyTypeFactory,
    private val clickListener: CurrencyClickListener
) : RecyclerView.Adapter<AbstractCurrencyViewHolder<CurrencyAbstractModel>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractCurrencyViewHolder<CurrencyAbstractModel> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return typeFactory.holder(viewType, view) as AbstractCurrencyViewHolder<CurrencyAbstractModel>
    }

    override fun getItemCount(): Int = currencies.count()

    override fun onBindViewHolder(holder: AbstractCurrencyViewHolder<CurrencyAbstractModel>, position: Int) {
        holder.bind(currencies[position],clickListener,position)
    }

    override fun getItemViewType(position: Int): Int {
        return currencies[position].type(typeFactory)
    }

    fun updateItems(newRates: List<CurrencyAbstractModel>) {
        currencies.clear()
        currencies.addAll(newRates)
        notifyDataSetChanged()
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (fromPosition == toPosition) return
        val oldCurrencyItem = currencies[fromPosition] as CurrencyModel
        val newBaseCurrencyItem = BaseCurrencyModel(BaseCurrency( oldCurrencyItem.currency.isoCode,oldCurrencyItem.currency.name, BigDecimal.ONE,oldCurrencyItem.currency.flagUrl))
        currencies.add(toPosition, newBaseCurrencyItem)
        currencies.removeAt(fromPosition)
        notifyItemMoved(fromPosition, toPosition)
    }
}