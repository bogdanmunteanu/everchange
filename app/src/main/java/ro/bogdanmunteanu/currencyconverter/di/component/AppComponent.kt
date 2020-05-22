package ro.bogdanmunteanu.currencyconverter.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

import ro.bogdanmunteanu.currencyconverter.EverchangeApp
import ro.bogdanmunteanu.currencyconverter.di.module.*
import ro.bogdanmunteanu.currencyconverter.di.module.ActivityModule
import ro.bogdanmunteanu.currencyconverter.di.module.FragmentModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,AppModule::class,ActivityModule::class,FragmentModule::class,NetworkModule::class,TestActivityModule::class,ViewModelModule::class,DatabaseModule::class])
interface AppComponent{

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: EverchangeApp): Builder

        fun build(): AppComponent
    }

    fun inject(application: EverchangeApp)
}