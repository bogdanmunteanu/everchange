package ro.bogdanmunteanu.currencyconverter.ui.holders

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.base_currency_item.view.*
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.BaseCurrencyModel
import ro.bogdanmunteanu.currencyconverter.ui.adapters.BaseCurrencyInputListener
import ro.bogdanmunteanu.currencyconverter.ui.adapters.CurrencyClickListener
import ro.bogdanmunteanu.currencyconverter.utils.DecimalDigitsInputFilter
import java.math.RoundingMode
import java.util.*

class BaseCurrencyViewHolder(view: View) : AbstractCurrencyViewHolder<BaseCurrencyModel>(view) {
    override fun bind(
        item: BaseCurrencyModel,
        clickListener: CurrencyClickListener,
        position: Int,
        textListener: BaseCurrencyInputListener
    ) {
        itemView.currencyTitle.text = item.baseCurrency.isoCode
        itemView.currencySubtitle.text = item.baseCurrency.name
        itemView.currencyInput.setText(
            String.format(
                Locale.getDefault(), item.baseCurrency.rate.setScale(
                    6,
                    RoundingMode.HALF_EVEN
                ).toString()
            )
        )
        itemView.currencyInput.let {
            it.setSelection(it.text.length)
        }
        Glide.with(itemView.context)
            .load(item.baseCurrency.flagUrl)
            .into(itemView.currencyImage)

        itemView.currencyInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.toString()?.let {
                    textListener.onTextChanged(it, item.baseCurrency.isoCode)
                }
            }
            override fun afterTextChanged(s: Editable?) {
                s?.filters = arrayOf(DecimalDigitsInputFilter(10, 6))
            }
        })

        itemView.currencyInput.setOnClickListener {
            clickListener.onItemClick(position, item)
        }
    }
}