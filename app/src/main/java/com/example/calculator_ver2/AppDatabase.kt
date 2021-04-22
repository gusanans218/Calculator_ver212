package com.example.calculator_ver2

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculator_ver2.dao.HistoryDao
import com.example.calculator_ver2.model.History

@Database(entities = [History::class], version = 1 )
abstract class AppDatabase: RoomDatabase(){
    abstract fun historyDao():HistoryDao
}