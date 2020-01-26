package ro.bogdanmunteanu.currencyconverter.persistence

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import ro.bogdanmunteanu.currencyconverter.data.model.Currency

@Database(entities = [Currency::class], version = 1)
@TypeConverters(BigDecimalTypeConverter::class)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao() : CurrencyDao

    companion object {
        private var instance: CurrencyDatabase? = null

        fun getInstance(context: Context): CurrencyDatabase? {
            if (instance == null) {
                synchronized(CurrencyDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CurrencyDatabase::class.java, "currencies")
                        .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: CurrencyDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val currencyDao = db?.currencyDao()

        override fun doInBackground(vararg p0: Unit?) {

        }
    }
}