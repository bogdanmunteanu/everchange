package ro.bogdanmunteanu.currencyconverter.persistence

import androidx.room.*
import io.reactivex.Maybe
import ro.bogdanmunteanu.currencyconverter.data.model.CurrencyResponse

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: CurrencyResponse)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(value: CurrencyResponse)

    @Delete
    fun delete(value: CurrencyResponse)

    @Query("DELETE FROM currencies")
    fun deleteAllCurrencies()

    @Query("SELECT * FROM currencies WHERE base=:base")
    fun getAllCurrencies(base: String): Maybe<CurrencyResponse>

}