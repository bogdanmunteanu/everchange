package ro.bogdanmunteanu.currencyconverter.di.module.fragments

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ro.bogdanmunteanu.currencyconverter.di.viewmodel.ViewModelKey
import ro.bogdanmunteanu.currencyconverter.ui.fragments.AboutFragment
import ro.bogdanmunteanu.currencyconverter.ui.fragments.CurrenciesFragment
import ro.bogdanmunteanu.currencyconverter.ui.fragments.PrivacyFragment
import ro.bogdanmunteanu.currencyconverter.ui.fragments.TermsFragment
import ro.bogdanmunteanu.currencyconverter.viewmodel.CurrenciesViewModel

@Module
internal abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeCurrenciesFragment() : CurrenciesFragment

    @ContributesAndroidInjector
    abstract fun contributeAboutFragment() : AboutFragment

    @ContributesAndroidInjector
    abstract fun contributeTermsFragment() : TermsFragment

    @ContributesAndroidInjector
    abstract fun contributePrivacyFragment() : PrivacyFragment
}