package ro.bogdanmunteanu.currencyconverter.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingUtils {

    @BindingAdapter("data")
    fun <T> setRecyclerViewProperties(recyclerView: RecyclerView,items : List<T>){
        if(recyclerView.adapter is BindableAdapter<*>) {
            (recyclerView.adapter as BindableAdapter<T>).setData(items)
        }

    }

    @BindingAdapter("changedPositions")
    fun <T> setDataChanged(recyclerView: RecyclerView,positions : Set<Int>){
        if(recyclerView.adapter is BindableAdapter<*>) {
            (recyclerView.adapter as BindableAdapter<T>).changedPositions(positions)
        }

    }
}