package com.sankha.daggerdemo2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat

@Entity(tableName = "WordTable")
data class WordEntity(@ColumnInfo(name="message") val message : String = "", @PrimaryKey @ColumnInfo(name = "timestamp") val timeStamp : String = "")