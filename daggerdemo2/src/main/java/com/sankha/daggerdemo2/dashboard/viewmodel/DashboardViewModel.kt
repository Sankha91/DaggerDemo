package com.sankha.daggerdemo2.dashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sankha.daggerdemo2.db.WordEntity
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter
import javax.inject.Inject

class DashboardViewModel @Inject constructor(private val dashboardRepository: DashboardRepository) : ViewModel() {

    @Inject
    lateinit var simpleDateFormat: SimpleDateFormat
    fun insert(msg: String) {
        dashboardRepository.insertWord(WordEntity(msg, simpleDateFormat.format(Date())))
    }

    fun getInsertLiveData() : MutableLiveData<Boolean>{
        return dashboardRepository.insertLiveData
    }
}