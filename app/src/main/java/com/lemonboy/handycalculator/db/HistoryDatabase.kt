package com.lemonboy.handycalculator.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lemonboy.handycalculator.converter.DateTypeConverter

@Database(entities = [HistoryItem::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class HistoryDatabase() : RoomDatabase() {
    abstract val historyDatabaseDao: HistoryDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        fun getInstance(context: Context) : HistoryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java,
                        "calculator_history_table"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}