package ro.bogdanmunteanu.currencyconverter.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.currency_item.view.*
import ro.bogdanmunteanu.currencyconverter.R
import ro.bogdanmunteanu.currencyconverter.data.model.BaseCurrencyRow
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyRate
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyRow






class CurrenciesAdapter(private val currencies:ArrayList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((Any) -> Unit)? = null
    var context: Context? = null
    var baseRate: Int? = null

    override fun getItemCount() = currencies.count()

    override fun getItemViewType(position: Int): Int =
        when (currencies[position]) {
            is BaseCurrencyRow -> TYPE_BASE_CURRENCY
            is CurrencyRow -> TYPE_CURRENCY
            else -> throw IllegalArgumentException()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_BASE_CURRENCY -> BaseCurrencyRowHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.base_currency_item, parent, false))
        TYPE_CURRENCY -> CurrencyRowHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_item, parent, false))
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
        //(holder as MessageViewHolder).messageView.text = row.message
    }

    //holders
    inner class CurrencyRowHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        init {
            itemView.container.setOnClickListener {
                onItemClick?.invoke(currencies[adapterPosition])
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(rate: CurrencyRate) {
            itemView.currencyTitle.text = rate.isoCode
            itemView.currencySubtitle.text = rate.name
            if(baseRate==null) {
                itemView.currencyInput.setText(rate.rate.toString())
            }else
            {
                itemView.currencyInput.setText(rate.rate.times(baseRate as Int).toString())
        }
            itemView.currencyInput.isEnabled = false
            itemView.currencyImage.setImageResource(getResId(rate.flagUrl,R.mipmap::class.java))
        }

    }

    inner class BaseCurrencyRowHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        @SuppressLint("SetTextI18n")
        fun bind(rate: CurrencyRate) {
            itemView.currencyTitle.text = rate.isoCode
            itemView.currencySubtitle.text = rate.name
            if(baseRate==null) {
                itemView.currencyInput.hint = rate.rate.toString()
            }else
            {
                itemView.currencyInput.setText(baseRate.toString())
            }
            itemView.currencyInput.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if(s!!.isEmpty())
                    {
                        baseRate=null
                    }else {
                        baseRate = s.toString().toInt()
                        itemView.currencyInput.setSelection(s!!.length)
                    }
                }
            })
            itemView.currencyImage.setImageResource(getResId(rate.flagUrl,R.mipmap::class.java))

        }
    }

    companion object {
        private const val TYPE_BASE_CURRENCY = 0
        private const val TYPE_CURRENCY = 1
    }

    fun updateItems(newRates: List<Any>)
    {
        currencies.clear()
        currencies.addAll(newRates)
        notifyDataSetChanged()
    }


    fun getResId(resName: String, c: Class<*>): Int {

        try {
            val idField = c.getDeclaredField(resName)
            return idField.getInt(idField)
        } catch (e: Exception) {
            e.printStackTrace()
            return -1
        }

    }





}