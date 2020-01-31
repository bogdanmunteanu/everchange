package ro.bogdanmunteanu.currencyconverter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ro.bogdanmunteanu.currencyconverter.data.api.OfflineException
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyResponse
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.BaseCurrencyModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyAbstractModel
import ro.bogdanmunteanu.currencyconverter.data.model.bindings.CurrencyModel
import ro.bogdanmunteanu.currencyconverter.data.repository.RevolutServiceRepository
import ro.bogdanmunteanu.currencyconverter.utils.CurrencyDisposable
import ro.bogdanmunteanu.currencyconverter.utils.CurrencyMapper
import java.math.BigDecimal
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrenciesViewModel @Inject constructor(private val service: RevolutServiceRepository) :
    ViewModel() {

    private val TAG = CurrenciesViewModel::class.java.simpleName

    private var disposables = CurrencyDisposable()

    private var mCurrencies: MutableLiveData<List<CurrencyAbstractModel>> = MutableLiveData()
    val currencies: LiveData<List<CurrencyAbstractModel>> get() = mCurrencies

    private var mFetchState: MutableLiveData<State> = MutableLiveData()
    val fetchState: LiveData<State> get() = mFetchState

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    init {
        mFetchState.value = State.DONE
    }

    fun getLiveCurrencies(baseCurrency: String, input: String) {
        disposables.clear() //need to clear the disposables because we need to make API call with new parameter
        mFetchState.value = State.IN_PROGRESS
        disposables.add(
            service.getCurrenciesFromEndpoint(baseCurrency, input)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(1, TimeUnit.SECONDS)
                .repeat()
                .map { t: CurrencyResponse -> CurrencyMapper.mapCurrencies(t, input) }
                .subscribe({ list ->
                    mCurrencies.postValue(setupListForView(input, list))
                }, { error ->
                    if (error is OfflineException) {
                        mFetchState.postValue(State.OFFLINE)
                    } else {
                        mFetchState.postValue(State.ERROR)
                        Log.e(TAG, Log.getStackTraceString(error))
                    }
                })
        )
    }

    private fun setupListForView(
        input: String,
        originalList: List<CurrencyAbstractModel>
    ): List<CurrencyAbstractModel> {

        val finalList = ArrayList<CurrencyAbstractModel>()
        val sortedList = ArrayList<CurrencyModel>()

        originalList.forEach { model ->
            when (model) {
                is BaseCurrencyModel -> {
                    finalList.add(0, model)
                }
                is CurrencyModel -> {
                    if (input.isEmpty()) {
                        model.currency.rate = model.currency.rate.times(BigDecimal.ONE)
                    } else {
                        model.currency.rate = model.currency.rate.times(input.toBigDecimal())
                    }
                    sortedList.add(model)
                }
            }
        }
        val rates = sortedList.sortedWith(compareBy { it.currency.priority })
        finalList.addAll(rates)
        return finalList.toList()
    }


    enum class State { DONE, IN_PROGRESS, ERROR, OFFLINE }

}