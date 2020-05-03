package com.sankha.daggerdemo2.dashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sankha.daggerdemo2.db.WordDao
import com.sankha.daggerdemo2.db.WordEntity
import com.sankha.daggerdemo2.db.WordRoomDatabase
import javax.inject.Inject

class DashboardRepository @Inject constructor(wordRoomDatabase: WordRoomDatabase){

    @Inject
    lateinit var insertLiveData: MutableLiveData<Boolean>
    @Inject
    lateinit var wordDao : WordDao

    fun insertWord(wordEntity: WordEntity){
        // For Testing
        insertLiveData.postValue(true)
    }
}