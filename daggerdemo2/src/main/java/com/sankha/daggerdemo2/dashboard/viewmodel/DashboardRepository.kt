package com.sankha.daggerdemo2.dashboard.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sankha.daggerdemo2.db.WordDao
import com.sankha.daggerdemo2.db.WordEntity
import com.sankha.daggerdemo2.db.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class DashboardRepository @Inject constructor(wordRoomDatabase: WordRoomDatabase){

    @Inject
    lateinit var insertLiveData: MutableLiveData<Boolean>
    @Inject
    lateinit var fetchLiveData: MutableLiveData<List<WordEntity>>
    @Inject
    lateinit var wordDao : WordDao

    fun insertWord(wordEntity: WordEntity){
        wordDao.insert(wordEntity)
        insertLiveData.postValue(true)
    }

    fun fetchAll() {
        fetchLiveData.postValue(wordDao.fetchAll())
    }
}