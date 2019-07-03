package ro.bogdanmunteanu.currencyconverter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ro.bogdanmunteanu.currencyconverter.data.api.OfflineException
import ro.bogdanmunteanu.currencyconverter.data.di.RevolutApiService
import ro.bogdanmunteanu.currencyconverter.data.model.Currencies
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrenciesViewModel @Inject constructor(val service: RevolutApiService): ViewModel() {

    private val TAG = CurrenciesViewModel::class.java.simpleName

    private var disposables: CompositeDisposable = CompositeDisposable()

    private var mCurrencies : MutableLiveData<Currencies> = MutableLiveData()
    val currencies : LiveData<Currencies> get() = mCurrencies

    private var mFetchState : MutableLiveData<State> = MutableLiveData()
    val fetchState : LiveData<State> get() = mFetchState

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    init {
        getLiveCurrencies("EUR")
    }


    fun getLiveCurrencies(baseCurrency: String)
    {
        //another way to do this is
        //Observable.wrap(service.getCurrencies(baseCurrency).delay(1,TimeUnit.SECONDS)).repeat();

        mFetchState.value = State.IN_PROGRESS
        disposables.add(Observable.interval(0,1000,TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .flatMap { service.getCurrencies(baseCurrency) }
            .subscribe({
                mCurrencies.value=it
            },{
                error->if(error is OfflineException){
                mFetchState.value = State.OFFLINE
                Log.e(TAG,"Device offline")
            } else {
                mFetchState.value = State.ERROR
                Log.e(TAG, Log.getStackTraceString(error))
            }
            }))
    }


    enum class State {DONE,IN_PROGRESS,ERROR,OFFLINE}

}