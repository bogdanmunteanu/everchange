package ro.bogdanmunteanu.currencyconverter.ui.holders

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.bumptech.glide.Glide
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.base_currency_item.view.*
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.BaseCurrencyModel
import ro.bogdanmunteanu.currencyconverter.ui.adapters.CurrencyClickListener
import ro.bogdanmunteanu.currencyconverter.utils.DecimalDigitsInputFilter

class BaseCurrencyViewHolder(view : View) : AbstractCurrencyViewHolder<BaseCurrencyModel>(view) {
    override fun bind(item: BaseCurrencyModel) {
        itemView.currencyTitle.text = item.baseCurrency.isoCode
        itemView.currencySubtitle.text = item.baseCurrency.name
        itemView.currencyInput.hint = item.baseCurrency.rate.toString()
        if(!itemView.currencyInput.hasFocus()) {
            itemView.currencyInput.requestFocus()
        }
        Glide.with(itemView.context)
            .load(item.baseCurrency.flagUrl)
            .into(itemView.currencyImage)

        itemView.currencyInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                s?.filters = arrayOf(DecimalDigitsInputFilter(10,2))
                s?.toString()?.let {
                }
            }
        })
    }
}