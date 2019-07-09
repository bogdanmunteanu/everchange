package ro.bogdanmunteanu.currencyconverter.di.module.test

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ro.bogdanmunteanu.currencyconverter.di.module.fragments.CurrenciesFragmentModule
import ro.bogdanmunteanu.currencyconverter.di.viewmodel.ViewModelFactory
import ro.bogdanmunteanu.currencyconverter.ui.activities.MainActivity
import ro.bogdanmunteanu.currencyconverter.ui.activities.SingleFragmentActivity

/**
 * Thest module that will be injected by dagger to create TestActivity
 */

@Module
internal abstract class TestActivityModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //inject all modules into main activity
    @ContributesAndroidInjector(modules = [CurrenciesFragmentModule::class])
    internal abstract fun contributeMainActivity(): SingleFragmentActivity
}