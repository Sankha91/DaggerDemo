package com.sankha.daggerdemo2.dashboard.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sankha.daggerdemo2.MyApplication
import com.sankha.daggerdemo2.db.WordDao
import com.sankha.daggerdemo2.db.WordEntity
import com.sankha.daggerdemo2.db.WordRoomDatabase
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Named

class DashboardRepository @Inject constructor(wordRoomDatabase: WordRoomDatabase){

    @Named("insert")
    @Inject
    lateinit var insertLiveData: MutableLiveData<Boolean>
    @Named("deleteAll")
    @Inject
    lateinit var deleteLiveData: MutableLiveData<Boolean>
    @Inject
    lateinit var fetchLiveData: MutableLiveData<List<WordEntity>>
    @Inject
    lateinit var wordDao : WordDao

    fun insertWord(wordEntity: WordEntity){
        runBlocking {
            wordDao.insert(wordEntity)
        }
        insertLiveData.postValue(true)
    }

    fun fetchAll() {
        fetchLiveData.postValue(wordDao.fetchAll())
    }

    fun deleteAll(){
        runBlocking {
            wordDao.deleteAll()
        }
        deleteLiveData.postValue(true)
    }

    fun deleteSingleItem(wordEntity: WordEntity) {
        runBlocking {
            wordDao.deleteWord(wordEntity)
        }
        deleteLiveData.postValue(true)
    }
}