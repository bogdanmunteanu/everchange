package ro.bogdanmunteanu.currencyconverter.ui.holders

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.currency_item.view.*
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyModel
import ro.bogdanmunteanu.currencyconverter.ui.adapters.BaseCurrencyInputListener
import ro.bogdanmunteanu.currencyconverter.ui.adapters.CurrencyClickListener
import java.math.RoundingMode

class CurrencyViewHolder(view: View) : AbstractCurrencyViewHolder<CurrencyModel>(view) {
    override fun bind(item: CurrencyModel,clickListener: CurrencyClickListener,position:Int,textListener: BaseCurrencyInputListener) {

        itemView.currencyTitle.text = item.currency.isoCode
        itemView.currencySubtitle.text = item.currency.name
        itemView.currencyInput.hint=item.currency.rate.setScale(2, RoundingMode.HALF_EVEN).toString()
        itemView.currencyInput.isEnabled = false
        itemView.currencyInput.text= ""
        Glide.with(itemView.context)
            .load(item.currency.flagUrl)
            .into(itemView.currencyImage)
        itemView.setOnClickListener { clickListener.onItemClick(position,item) }
    }
}