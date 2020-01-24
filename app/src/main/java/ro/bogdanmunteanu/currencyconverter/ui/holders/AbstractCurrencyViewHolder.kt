package ro.bogdanmunteanu.currencyconverter.ui.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ro.bogdanmunteanu.currencyconverter.ui.adapters.CurrencyClickListener

/**
 *  Abstract view holder that is the base for the holders included with the visitor pattern
 */
abstract class AbstractCurrencyViewHolder<in T>(view : View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item : T)
}