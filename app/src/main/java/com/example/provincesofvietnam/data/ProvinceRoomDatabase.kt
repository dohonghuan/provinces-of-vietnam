package com.example.provincesofvietnam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [DatabaseProvince::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProvinceRoomDatabase: RoomDatabase() {
    abstract val provinceDao: ProvinceDao

    companion object {
        private lateinit var INSTANCE: ProvinceRoomDatabase

        fun getDatabase(context: Context): ProvinceRoomDatabase {
            synchronized(ProvinceRoomDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ProvinceRoomDatabase::class.java,
                        "provinces").build()
                }
            }
            return INSTANCE
        }
    }
}
