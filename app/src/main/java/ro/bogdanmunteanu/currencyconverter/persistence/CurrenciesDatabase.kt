package ro.bogdanmunteanu.currencyconverter.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ro.bogdanmunteanu.currencyconverter.data.model.Currency
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyResponse

@Database(entities = [CurrencyResponse::class], version = 2,exportSchema = false)
@TypeConverters(HashMapTypeConverter::class)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao() : CurrencyDao

    companion object {
        const val DATABASE_NAME = "currencies.db"
    }
}