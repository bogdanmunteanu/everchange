package ro.bogdanmunteanu.currencyconverter.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import ro.bogdanmunteanu.currencyconverter.Application
import ro.bogdanmunteanu.currencyconverter.di.module.network.NetworkModule
import ro.bogdanmunteanu.currencyconverter.di.module.persistence.DatabaseModule

/**
 * Application-wide dependencies
 */
@Module(includes = [NetworkModule::class,DatabaseModule::class])
abstract class AppModule {

    @Binds
    abstract fun provideContext(application: Application) : Context

}