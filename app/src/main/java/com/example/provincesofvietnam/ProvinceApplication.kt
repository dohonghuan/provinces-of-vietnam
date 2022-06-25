package com.example.provincesofvietnam

import android.app.Application
import com.example.provincesofvietnam.data.ProvinceRoomDatabase
import com.example.provincesofvietnam.repository.ProvinceRepository

class ProvinceApplication: Application() {
    private val database by lazy { ProvinceRoomDatabase.getDatabase(this) }
    val repository by lazy { ProvinceRepository(database.provinceDao) }
}