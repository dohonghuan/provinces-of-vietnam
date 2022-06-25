package com.example.provincesofvietnam.network

import com.example.provincesofvietnam.data.DatabaseProvince
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProvinceProperty(
    @Json(name = "name") val name: String,
    @Json(name = "code") val code: Int,
    @Json(name = "division_type") val divisionType: String,
    @Json(name = "codename") val codename: String,
    @Json(name = "phone_code") val phoneCode: Double,
    @Json(name = "districts") val districts: List<String> = listOf()
)

fun List<ProvinceProperty>.asDatabaseModel(): List<DatabaseProvince> {
    return map {
        DatabaseProvince(
            name = it.name,
            code = it.code,
            divisionType = it.divisionType,
            codeName = it.codename,
            phoneCode = it.phoneCode,
            districts = it.districts
        )
    }
}