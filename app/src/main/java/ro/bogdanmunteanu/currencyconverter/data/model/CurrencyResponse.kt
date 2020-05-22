package ro.bogdanmunteanu.currencyconverter.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
class CurrencyResponse constructor(
    @PrimaryKey
    @ColumnInfo(name = "base")
    val base: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "rates")
    val rates: HashMap<String, String>
)