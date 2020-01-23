package ro.bogdanmunteanu.currencyconverter.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.currency_item.view.*
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.data.model.BaseCurrencyRow
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyRate
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyRow
import ro.bogdanmunteanu.currencyconverter.utils.DecimalDigitsInputFilter
import java.math.BigDecimal
import java.math.RoundingMode


class CurrenciesAdapter(private val currencies: ArrayList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((Pair<Int,Any>) -> Unit)? = null
    var context: Context? = null
    var inputSubject: PublishSubject<String> = PublishSubject.create<String>()


    override fun getItemCount() = currencies.count()

    override fun getItemViewType(position: Int): Int =
        when (currencies[position]) {
            is BaseCurrencyRow -> TYPE_BASE_CURRENCY
            is CurrencyRow -> TYPE_CURRENCY
            else -> throw IllegalArgumentException()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_BASE_CURRENCY -> BaseCurrencyRowHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.base_currency_item, parent, false)
        )
        TYPE_CURRENCY -> CurrencyRowHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.currency_item, parent, false)
        )
        else -> throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            TYPE_BASE_CURRENCY -> onBindBaseCurrency(holder, currencies[position] as BaseCurrencyRow)
            TYPE_CURRENCY -> onBindCurrency(holder, currencies[position] as CurrencyRow)
            else -> throw IllegalArgumentException()
        }

    private fun onBindBaseCurrency(holder: RecyclerView.ViewHolder, baseCurrency: BaseCurrencyRow) {
        val baseCurrencyRowHolder = holder as BaseCurrencyRowHolder
        baseCurrencyRowHolder.bind(baseCurrency)
    }

    private fun onBindCurrency(holder: RecyclerView.ViewHolder, currency: CurrencyRow) {
        val currencyHolder = holder as CurrencyRowHolder
        currencyHolder.bind(currency)

    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (fromPosition == toPosition) return

        val oldCurrencyItem = currencies[fromPosition] as CurrencyRow
        val newBaseCurrencyItem = BaseCurrencyRow(oldCurrencyItem.isoCode,oldCurrencyItem.name, BigDecimal.ONE,oldCurrencyItem.flagUrl)
        currencies.add(toPosition, newBaseCurrencyItem)
        currencies.removeAt(fromPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    //holders
    inner class CurrencyRowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(Pair(adapterPosition,currencies[adapterPosition]))
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(rate: CurrencyRate) {
            itemView.currencyTitle.text = rate.isoCode
            itemView.currencySubtitle.text = rate.name
            itemView.currencyInput.setText(rate.rate.setScale(2,RoundingMode.HALF_EVEN).toString())
            itemView.currencyInput.isEnabled = false
            Glide.with(itemView.context)
                .load(rate.flagUrl)
                .into(itemView.currencyImage)
        }

    }

    inner class BaseCurrencyRowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(rate: CurrencyRate) {
            itemView.currencyTitle.text = rate.isoCode
            itemView.currencySubtitle.text = rate.name
            itemView.currencyInput.hint = rate.rate.toString()
            itemView.currencyInput.text = ""
            itemView.currencyInput.requestFocus()
            Glide.with(itemView.context)
                .load(rate.flagUrl)
                .into(itemView.currencyImage)


            itemView.currencyInput.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    s?.filters = arrayOf(DecimalDigitsInputFilter(10,2))
                    s?.toString()?.let {
                        inputSubject.onNext(it)
                    }
                }
            })
        }
    }

    companion object {
        private const val TYPE_BASE_CURRENCY = 0
        private const val TYPE_CURRENCY = 1
    }

    fun updateItems(newRates: List<Any>) {
        currencies.clear()
        currencies.addAll(newRates)
        notifyDataSetChanged()
    }
}