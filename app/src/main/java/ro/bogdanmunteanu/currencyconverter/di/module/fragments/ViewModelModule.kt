package ro.bogdanmunteanu.currencyconverter.di.module.fragments

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ro.bogdanmunteanu.currencyconverter.di.viewmodel.ViewModelKey
import ro.bogdanmunteanu.currencyconverter.ui.fragments.CurrenciesFragment
import ro.bogdanmunteanu.currencyconverter.viewmodel.CurrenciesViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrenciesViewModel::class)
    abstract fun bindCurrenciesViewModel(viewModel: CurrenciesViewModel): ViewModel

}