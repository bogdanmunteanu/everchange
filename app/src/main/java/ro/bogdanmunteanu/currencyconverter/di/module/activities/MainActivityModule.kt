package ro.bogdanmunteanu.currencyconverter.di.module.activities

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ro.bogdanmunteanu.currencyconverter.di.module.fragments.CurrenciesFragmentModule
import ro.bogdanmunteanu.currencyconverter.di.viewmodel.ViewModelFactory
import ro.bogdanmunteanu.currencyconverter.ui.activities.MainActivity


@Module
internal abstract class MainActivityModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //inject all modules into main activity
    @ContributesAndroidInjector(modules = [CurrenciesFragmentModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}
