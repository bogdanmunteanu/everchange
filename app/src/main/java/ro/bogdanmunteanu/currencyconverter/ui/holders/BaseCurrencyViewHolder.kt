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

class BaseCurrencyViewHolder(view : View) : AbstractCurrencyViewHolder<BaseCurrencyModel>(view) {
    override fun bind(item: BaseCurrencyModel,clickListener: CurrencyClickListener,position : Int,textListener: BaseCurrencyInputListener) {
        itemView.currencyTitle.text = item.baseCurrency.isoCode
        itemView.currencySubtitle.text = item.baseCurrency.name
        itemView.currencyInput.setText(
            item.baseCurrency.rate.setScale(
                2,
                RoundingMode.HALF_EVEN
            ).toString()
        )
        Glide.with(itemView.context)
            .load(item.baseCurrency.flagUrl)
            .into(itemView.currencyImage)

        itemView.currencyInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                s?.filters = arrayOf(DecimalDigitsInputFilter(10,2))
                s?.toString()?.let {
                    itemView.currencyInput.setSelection(s.length)
                    textListener.onTextChanged(it,item.baseCurrency.isoCode)
                }
            }
        })

        itemView.currencyInput.setOnClickListener{
            clickListener.onItemClick(position,item)
        }
    }
}