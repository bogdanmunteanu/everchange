package ro.bogdanmunteanu.currencyconverter.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.bogdanmunteanu.currencyconverter.R

import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyRate



class CurrenciesAdapter(private val currencies:ArrayList<CurrencyRate>) : RecyclerView.Adapter<CurrenciesAdapter.Holder>() {

    var onItemClick: ((CurrencyRate) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val viewHolder = Holder(LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_item, parent, false))

        return viewHolder

    }

    override fun getItemCount(): Int = currencies.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(currencies[position])
    }

    inner class Holder(private val view: View) : RecyclerView.ViewHolder(view) {

        init {

        }

        fun bind(rate: CurrencyRate) {
           //bindings

        }
    }
}