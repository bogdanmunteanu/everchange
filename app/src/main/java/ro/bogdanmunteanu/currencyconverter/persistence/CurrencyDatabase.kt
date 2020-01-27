package ro.bogdanmunteanu.currencyconverter.persistence

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import ro.bogdanmunteanu.currencyconverter.data.model.Currency

@Database(entities = [Currency::class], version = 1,exportSchema = false)
@TypeConverters(BigDecimalTypeConverter::class)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao() : CurrencyDao

    companion object {
        const val DATABASE_NAME = "currencies.db"
    }
}