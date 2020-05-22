package ro.bogdanmunteanu.currencyconverter.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ro.bogdanmunteanu.currencyconverter.di.viewmodel.ViewModelFactory
import ro.bogdanmunteanu.currencyconverter.ui.activities.MainActivity


@Module
internal abstract class ActivityModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //inject all modules into main activity
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}
