package ro.bogdanmunteanu.currencyconverter.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import ro.bogdanmunteanu.currencyconverter.Application
import ro.bogdanmunteanu.currencyconverter.di.module.network.NetworkModule

/**
 * Application-wide dependencies
 */
@Module(includes = [NetworkModule::class])
abstract class AppModule {

    @Binds
    abstract fun provideContext(application: Application): Context

}