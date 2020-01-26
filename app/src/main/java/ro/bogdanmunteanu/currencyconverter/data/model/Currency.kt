package ro.bogdanmunteanu.currencyconverter.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "currencies")
data class Currency(
    @PrimaryKey
    @ColumnInfo(name = "isoCode")
    var isoCode: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "rate")
    var rate: BigDecimal,
    @ColumnInfo(name = "flag")
    var flagUrl: String
)