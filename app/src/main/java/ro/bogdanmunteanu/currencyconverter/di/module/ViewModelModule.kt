package ro.bogdanmunteanu.currencyconverter.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ro.bogdanmunteanu.currencyconverter.di.viewmodel.ViewModelKey
import ro.bogdanmunteanu.currencyconverter.viewmodel.CurrenciesViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrenciesViewModel::class)
    abstract fun bindCurrenciesViewModel(viewModel: CurrenciesViewModel): ViewModel

}