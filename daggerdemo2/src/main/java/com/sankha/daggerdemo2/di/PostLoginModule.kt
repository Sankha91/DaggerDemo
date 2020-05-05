package com.sankha.daggerdemo2.di

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.sankha.daggerdemo2.MyApplication
import com.sankha.daggerdemo2.ViewActivity
import com.sankha.daggerdemo2.db.WordEntity
import com.sankha.daggerdemo2.login.view.LoginActivity
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Job
import java.text.SimpleDateFormat
import javax.inject.Named
import javax.inject.Singleton


@Module
class PostLoginModule {

    @Named("GotoLogin")
    @Provides
    fun provideLoginIntent(appCompatActivity: AppCompatActivity) : Intent {
        return Intent(appCompatActivity, LoginActivity::class.java)
    }

    @Named("GotoView")
    @Provides
    fun provideViewIntent(appCompatActivity: AppCompatActivity) : Intent {
        return Intent(appCompatActivity, ViewActivity::class.java)
    }


    @Provides
    fun provideSimpleDateFormat(): SimpleDateFormat{
        return SimpleDateFormat("dd MMM yyyy HH:mm:ss z")
    }

    @Named("insert")
    @Provides
    fun provideInsertLiveData(): MutableLiveData<Boolean> {
        return MutableLiveData()
    }

    @Named("deleteAll")
    @Provides
    fun provideDeleteAllLiveData(): MutableLiveData<Boolean> {
        return MutableLiveData()
    }

    @Provides
    fun provideFetchLiveData(): MutableLiveData<List<WordEntity>> {
        return MutableLiveData()
    }

    @Provides
    fun provideAlertDialogBuilder(appCompatActivity: AppCompatActivity) : AlertDialog.Builder{
        return AlertDialog.Builder(appCompatActivity)
    }
}