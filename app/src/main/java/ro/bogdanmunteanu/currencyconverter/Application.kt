package ro.bogdanmunteanu.currencyconverter

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasActivityInjector
import ro.bogdanmunteanu.currencyconverter.di.component.DaggerAppComponent

class Application : DaggerApplication(), HasActivityInjector {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}