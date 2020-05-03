package com.sankha.daggerdemo2.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface WordDao {

    @Insert
    fun insert(wordEntity: WordEntity)
}