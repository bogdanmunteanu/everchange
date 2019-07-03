package ro.bogdanmunteanu.currencyconverter.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import ro.bogdanmunteanu.currencyconverter.Application
import ro.bogdanmunteanu.currencyconverter.di.module.AppModule
import ro.bogdanmunteanu.currencyconverter.di.module.activities.MainActivityModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, MainActivityModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application( application: Application) : Builder
        fun build(): AppComponent
    }

    fun inject(application:Application)
    override fun inject(instance: DaggerApplication)
}