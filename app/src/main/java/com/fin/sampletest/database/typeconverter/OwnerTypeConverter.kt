package com.fin.sampletest.database.typeconverter

import androidx.room.TypeConverter
import com.fin.sampletest.data.Owner

class OwnerTypeConverter {

    @TypeConverter
    fun fromTimestamp(value: String?): Owner? {
        return value?.let { Owner(it) }
    }

    @TypeConverter
    fun dateToTimestamp(value: Owner?): String? {
        return value?.toString()
    }
}