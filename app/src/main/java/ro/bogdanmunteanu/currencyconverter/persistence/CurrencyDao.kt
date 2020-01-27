package ro.bogdanmunteanu.currencyconverter.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import ro.bogdanmunteanu.currencyconverter.data.model.Currency

@Dao
interface CurrencyDao {
    @Insert
    fun insert(note: Currency)

    @Update
    fun update(note: Currency)

    @Delete
    fun delete(note: Currency)

    @Query("DELETE FROM currencies")
    fun deleteAllCurrencies()

    @Query("SELECT * FROM currencies")
    fun getAllCurrencies(): LiveData<List<Currency>>
    
    @Query("SELECT * FROM currencies WHERE isoCode=:isoCode")
    fun getCurrency(isoCode: String) : Currency
}