package com.sankha.daggerdemo2.dashboard.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sankha.daggerdemo2.db.WordEntity
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter
import javax.inject.Inject
import kotlin.collections.ArrayList
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

    fun getInsertLiveData() : MutableLiveData<Boolean>{
        return dashboardRepository.insertLiveData
    }

    fun fetchLiveData() : MutableLiveData<List<WordEntity>>{
        return dashboardRepository.fetchLiveData
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}