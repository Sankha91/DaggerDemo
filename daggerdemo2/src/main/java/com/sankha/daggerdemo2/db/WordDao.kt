package com.sankha.daggerdemo2.db

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {

    @Insert
    fun insert(wordEntity: WordEntity)

    @Query("SELECT * from WordTable ORDER BY timestamp ASC")
    fun fetchAll() : List<WordEntity>

    @Delete
    fun deleteWord(wordEntity: WordEntity)

    @Query("DELETE FROM WordTable")
    fun deleteAll()
}