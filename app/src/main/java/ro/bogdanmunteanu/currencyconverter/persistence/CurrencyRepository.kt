package ro.bogdanmunteanu.currencyconverter.persistence

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import ro.bogdanmunteanu.currencyconverter.data.model.Currency
import javax.inject.Inject

class CurrencyRepository @Inject constructor(appContext: Context) {


    private var currencyDao : CurrencyDao

    private var allCurrencies : LiveData<List<Currency>>

    init {

        val database: CurrencyDatabase = CurrencyDatabase.getInstance(appContext)!!
        currencyDao = database.currencyDao()
        allCurrencies = currencyDao.getAllCurrencies()
    }

    fun insert(currency: Currency) = InsertNoteAsyncTask(currencyDao).execute(currency)
    fun update(currency: Currency) = UpdateNoteAsyncTask(currencyDao).execute(currency)
    fun delete(currency: Currency) = DeleteNoteAsyncTask(currencyDao).execute(currency)
    fun deleteAllCurrencies() = DeleteAllNotesAsyncTask(currencyDao).execute()
    fun getCurrency(currency: Currency) = GetCurrencyAsyncTask(currencyDao).execute(currency.isoCode)

    fun getAllCurrencies(): LiveData<List<Currency>> {
        return allCurrencies
    }


    companion object {
        private class InsertNoteAsyncTask(currencyDao: CurrencyDao) : AsyncTask<Currency, Unit, Unit>() {
            val currencyDao = currencyDao

            override fun doInBackground(vararg p0: Currency?) {
                p0[0]?.let { currencyDao.insert(it) }
            }
        }

        private class UpdateNoteAsyncTask(currencyDao:  CurrencyDao) : AsyncTask<Currency, Unit, Unit>() {
            val currencyDao = currencyDao

            override fun doInBackground(vararg p0: Currency?) {
                p0[0]?.let { currencyDao.update(it) }
            }
        }

        private class DeleteNoteAsyncTask(currencyDao: CurrencyDao) : AsyncTask<Currency, Unit, Unit>() {
            val currencyDao = currencyDao

            override fun doInBackground(vararg p0: Currency?) {
                p0[0]?.let { currencyDao.delete(it) }
            }
        }

        private class DeleteAllNotesAsyncTask(currencyDao: CurrencyDao) : AsyncTask<Unit, Unit, Unit>() {
            val currencyDao = currencyDao

            override fun doInBackground(vararg p0: Unit) {
                currencyDao.deleteAllCurrencies()
            }
        }

        private class GetCurrencyAsyncTask(currencyDao: CurrencyDao) : AsyncTask<String, Unit, Unit>() {
            val currencyDao = currencyDao

            override fun doInBackground(vararg p0: String?) {
                p0[0]?.let { currencyDao.getCurrency(it) }
            }
        }
    }
}