package com.example.provincesofvietnam.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.provincesofvietnam.domain.ProvinceDomain
import com.google.gson.Gson

class Converters {
        @TypeConverter
        fun listToJsonString(value: List<String>?): String = Gson().toJson(value)

        @TypeConverter
        fun jsonStringToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}

@Entity(tableName = "province_table")
data class DatabaseProvince(
        @PrimaryKey @ColumnInfo(name = "code") val code: Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "division_type") val divisionType: String,
        @ColumnInfo(name = "code_name") val codeName: String,
        @ColumnInfo(name = "phone_code") val phoneCode: Double,
        @ColumnInfo(name = "districts") val districts: List<String> = listOf()
)

/**
 * Map DatabaseProvince to domain entities
 */
fun List<DatabaseProvince>.asDomainModel(): List<ProvinceDomain> {
        return map {
                ProvinceDomain(
                        code = it.code,
                        name = it.name,
                        divisionType = it.divisionType,
                )
        }
}
