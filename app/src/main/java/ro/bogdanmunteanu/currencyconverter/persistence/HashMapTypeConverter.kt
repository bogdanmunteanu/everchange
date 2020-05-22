package ro.bogdanmunteanu.currencyconverter.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class HashMapTypeConverter {
    @TypeConverter
    fun fromString(value: String?): HashMap<String?, String?>? {
        val mapType: Type = object :
            TypeToken<HashMap<String?, String?>?>() {}.type
        return Gson().fromJson(
            value,
            mapType
        )
    }

    @TypeConverter
    fun fromStringMap(map: HashMap<String?, String?>?): String? {
        val gson = Gson()
        return gson.toJson(map)
    }

}