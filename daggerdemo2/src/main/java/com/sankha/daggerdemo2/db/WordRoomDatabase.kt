package com.sankha.daggerdemo2.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(WordEntity::class), version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao() : WordDao
}