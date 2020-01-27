package ro.bogdanmunteanu.currencyconverter.di.module.persistence

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ro.bogdanmunteanu.currencyconverter.persistence.CurrencyDao
import ro.bogdanmunteanu.currencyconverter.persistence.CurrencyDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCurrencyDatabase(context: Context) : CurrencyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CurrencyDatabase::class.java, CurrencyDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyDao(database: CurrencyDatabase) : CurrencyDao{
        return database.currencyDao()
    }
}