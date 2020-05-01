package com.sankha.daggerdemo2.di

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.sankha.daggerdemo2.login.view.LoginActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class PostLoginModule {

    @Provides
    fun provideLoginIntent(appCompatActivity: AppCompatActivity) : Intent {
        return Intent(appCompatActivity, LoginActivity::class.java)
    }
}