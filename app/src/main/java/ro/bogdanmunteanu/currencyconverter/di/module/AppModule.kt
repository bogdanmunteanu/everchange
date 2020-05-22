package ro.bogdanmunteanu.currencyconverter.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ro.bogdanmunteanu.currencyconverter.EverchangeApp
import javax.inject.Singleton

/**
 * Application-wide dependencies
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: EverchangeApp): Context = application.applicationContext

}