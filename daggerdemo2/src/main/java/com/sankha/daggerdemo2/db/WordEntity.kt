package com.sankha.daggerdemo2.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat

@Entity(tableName = "WordTable")
data class WordEntity(val message : String = "", @PrimaryKey val timeStamp : String = "")