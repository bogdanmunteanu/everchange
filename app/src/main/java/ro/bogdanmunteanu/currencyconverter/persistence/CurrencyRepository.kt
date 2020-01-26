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

    fun insert(currency: Currency) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(currencyDao).execute(currency)
    }

    fun update(currency: Currency) {
        val updateNoteAsyncTask = UpdateNoteAsyncTask(currencyDao).execute(currency)
    }


    fun delete(currency: Currency) {
        val deleteNoteAsyncTask = DeleteNoteAsyncTask(currencyDao).execute(currency)
    }

    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(currencyDao).execute()
    }

    fun getAllNotes(): LiveData<List<Currency>> {
        return allCurrencies
    }


    companion object {
        private class InsertNoteAsyncTask(currencyDao: CurrencyDao) : AsyncTask<Currency, Unit, Unit>() {
            val currencyDao = currencyDao

            override fun doInBackground(vararg p0: Currency?) {
                currencyDao.insert(p0[0]!!)
            }
        }

        private class UpdateNoteAsyncTask(currencyDao:  CurrencyDao) : AsyncTask<Currency, Unit, Unit>() {
            val currencyDao = currencyDao

            override fun doInBackground(vararg p0: Currency?) {
                currencyDao.update(p0[0]!!)
            }
        }

        private class DeleteNoteAsyncTask(currencyDao: CurrencyDao) : AsyncTask<Currency, Unit, Unit>() {
            val currencyDao = currencyDao

            override fun doInBackground(vararg p0: Currency?) {
                currencyDao.delete(p0[0]!!)
            }
        }

        private class DeleteAllNotesAsyncTask(currencyDao: CurrencyDao) : AsyncTask<Unit, Unit, Unit>() {
            val currencyDao = currencyDao

            override fun doInBackground(vararg p0: Unit?) {
                currencyDao.deleteAllCurrencies()
            }
        }
    }
}