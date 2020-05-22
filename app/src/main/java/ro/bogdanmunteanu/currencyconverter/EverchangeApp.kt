package ro.bogdanmunteanu.currencyconverter

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import ro.bogdanmunteanu.currencyconverter.di.component.DaggerAppComponent

import javax.inject.Inject

class EverchangeApp : Application(), HasActivityInjector {

    private val TAG = EverchangeApp::class.java.name

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>


    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
}