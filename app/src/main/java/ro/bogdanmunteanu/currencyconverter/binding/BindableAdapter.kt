package ro.bogdanmunteanu.currencyconverter.binding

interface BindableAdapter<T> {

    fun setData(items : List<T>)
    fun changedPositions(positions : Set<Int>)
}