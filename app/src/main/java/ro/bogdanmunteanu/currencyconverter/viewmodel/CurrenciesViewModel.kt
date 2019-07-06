package ro.bogdanmunteanu.currencyconverter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import ro.bogdanmunteanu.currencyconverter.data.api.OfflineException
import ro.bogdanmunteanu.currencyconverter.data.model.Currencies
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyRate
import ro.bogdanmunteanu.currencyconverter.data.repository.RevolutServiceRepository
import ro.bogdanmunteanu.currencyconverter.utils.CurrencyDisposable
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrenciesViewModel @Inject constructor(val service: RevolutServiceRepository): ViewModel() {

    private val TAG = CurrenciesViewModel::class.java.simpleName

    private var disposables = CurrencyDisposable()

    private var mCurrencies : MutableLiveData<List<CurrencyRate>> = MutableLiveData()
    val currencies : LiveData<List<CurrencyRate>> get() = mCurrencies

    private var mFetchState : MutableLiveData<State> = MutableLiveData()
    val fetchState : LiveData<State> get() = mFetchState

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    init {
        mFetchState.value = State.DONE
    }

    fun getLiveCurrencies(baseCurrency: String)
    {
        //another way to do this is
        //Observable.wrap(service.getCurrencies(baseCurrency).delay(1,TimeUnit.SECONDS)).repeat();
        //

        mFetchState.value = State.IN_PROGRESS
        disposables.add(service.getCurrencies(baseCurrency)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(1,TimeUnit.SECONDS)
            .repeat()
            .subscribe({ rates : List<CurrencyRate>->
                mCurrencies.postValue(rates)
            },{
                Log.e(TAG,it.message)
            }))

    }


    enum class State {DONE,IN_PROGRESS,ERROR,OFFLINE}

}