package ro.bogdanmunteanu.currencyconverter.di.module.test

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ro.bogdanmunteanu.currencyconverter.di.module.fragments.FragmentModule
import ro.bogdanmunteanu.currencyconverter.di.module.fragments.ViewModelModule
import ro.bogdanmunteanu.currencyconverter.di.viewmodel.ViewModelFactory
import ro.bogdanmunteanu.currencyconverter.ui.activities.SingleFragmentActivity

/**
 * Thest module that will be injected by dagger to create TestActivity
 */

@Module
internal abstract class TestActivityModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //inject all modules into main activity
    @ContributesAndroidInjector(modules = [ViewModelModule::class,FragmentModule::class])
    internal abstract fun contributeSingleFragmentActivity(): SingleFragmentActivity
}
