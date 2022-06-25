package com.example.provincesofvietnam.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProvinceDao {
    @Query("SELECT * from province_table ORDER BY code ASC")
    fun getAllProvinces(): LiveData<List<DatabaseProvince>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(provinces: List<DatabaseProvince>)

    @Query("SELECT * from province_table WHERE code = :code")
    suspend fun getProvince(code: Int): DatabaseProvince
}
