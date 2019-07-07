package ro.bogdanmunteanu.currencyconverter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ro.bogdanmunteanu.currencyconverter.data.api.OfflineException

import ro.bogdanmunteanu.currencyconverter.data.repository.RevolutServiceRepository
import ro.bogdanmunteanu.currencyconverter.ui.CurrencyMapper
import ro.bogdanmunteanu.currencyconverter.utils.CurrencyDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrenciesViewModel @Inject constructor(val service: RevolutServiceRepository): ViewModel() {

    private val TAG = CurrenciesViewModel::class.java.simpleName

    private var disposables = CurrencyDisposable()


    private var mCurrencies : MutableLiveData<List<Any>> = MutableLiveData()
    val currencies : LiveData<List<Any>> get() = mCurrencies

    private var mFetchState : MutableLiveData<State> = MutableLiveData()
    val fetchState : LiveData<State> get() = mFetchState

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    init {
        mFetchState.value = State.DONE
    }

    fun getLiveCurrencies(baseCurrency:String)
    {
        disposables.clear() //need to clear the disposables because we need to make API call with new parameter
        mFetchState.value = State.IN_PROGRESS
        disposables.add(service.getCurrencies(baseCurrency)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(1,TimeUnit.SECONDS)
            .repeat()
            .subscribe({ rates : List<Any>->
                mCurrencies.postValue(rates)
            },{
                    error->if(error is OfflineException){
                mFetchState.postValue(State.OFFLINE)
                Log.e(TAG,"Device offline")
            } else {
                mFetchState.postValue(State.OFFLINE)
                Log.e(TAG,Log.getStackTraceString(error))
            }
            }))

    }


    enum class State {DONE,IN_PROGRESS,ERROR,OFFLINE}

}