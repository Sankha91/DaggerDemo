package com.sankha.daggerdemo2.dashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sankha.daggerdemo2.db.WordEntity
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DashboardViewModel @Inject constructor(private val dashboardRepository: DashboardRepository) : ViewModel(),
    CoroutineScope {

    @Inject
    lateinit var simpleDateFormat: SimpleDateFormat
    var job : Job
    init {
        job = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    fun insert(msg: String) {
        launch {
            dashboardRepository.insertWord(WordEntity(msg, simpleDateFormat.format(Date())))
        }
    }

    fun fetchAll() {
        launch {
            async(Dispatchers.IO){dashboardRepository.fetchAll()}.await()
        }
    }

    fun deleteAll() {
        launch {
            dashboardRepository.deleteAll()
        }
    }

    fun delete(wordEntity: WordEntity){
        launch {
            dashboardRepository.deleteSingleItem(wordEntity)
        }
    }

    fun getInsertLiveData() : MutableLiveData<Boolean>{
        return dashboardRepository.insertLiveData
    }

    fun getDeleteLiveData() : MutableLiveData<Boolean>{
        return dashboardRepository.deleteLiveData
    }

    fun fetchLiveData() : MutableLiveData<List<WordEntity>>{
        return dashboardRepository.fetchLiveData
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}