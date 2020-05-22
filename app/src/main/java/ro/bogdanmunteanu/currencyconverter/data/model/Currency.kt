package ro.bogdanmunteanu.currencyconverter.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal


data class Currency(
    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    @ColumnInfo(name = "base")
    var base:String,
    @ColumnInfo(name = "isoCode")
    var isoCode: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "rate")
    var rate: BigDecimal,
    @ColumnInfo(name = "flagUrl")
    var flagUrl:String,
    @ColumnInfo(name = "priority")
    var priority: Int
)