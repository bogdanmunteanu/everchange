package ro.bogdanmunteanu.currencyconverter.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

//simple singleton to reuse composite disposable

class CurrencyDisposable {
    private var compositeDisposable: CompositeDisposable? = null

    fun add(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }

    fun dispose() {
        compositeDisposable?.dispose()
        compositeDisposable = null
    }
}