package com.sankha.daggerdemo2.di

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.sankha.daggerdemo2.dashboard.DashboardActivity
import com.sankha.daggerdemo2.login.model.UserResponseModel
import com.sankha.daggerdemo2.login.viewmodel.LoginDataSource
import com.sankha.daggerdemo2.login.viewmodel.LoginDataSourceImpl
import com.sankha.daggerdemo2.login.viewmodel.LoginLocalDataSource
import com.sankha.daggerdemo2.login.viewmodel.LoginService
import com.sankha.daggerdemo2.login.view.LoginActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.*
import javax.inject.Named

@Module
class PreLoginModule() {

    @Named("GotoLogin")
    @Provides
    fun provideSplashIntent(appCompatActivity: AppCompatActivity) : Intent {
        return Intent(appCompatActivity, LoginActivity::class.java)
    }

    @Named("GotoDashboard")
    @Provides
    fun provideLoginIntent(appCompatActivity: AppCompatActivity) : Intent {
        return Intent(appCompatActivity, DashboardActivity::class.java)
    }
    @Provides
    fun provideTimer() : Timer{
         return Timer()
    }

    @Provides
    fun provideLoginApiService(retrofit: Retrofit) : LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    fun provideLoginRemoteDataSource(loginRemoteDataSource: LoginDataSourceImpl) : LoginDataSource {
        return loginRemoteDataSource
    }

    @Provides
    fun provideLoginLocalDataSource(loginLocalDataSource: LoginDataSourceImpl) : LoginLocalDataSource {
        return loginLocalDataSource
    }

    @Provides
    fun provideLoginLiveData(): MutableLiveData<UserResponseModel>{
        return MutableLiveData()
    }
}