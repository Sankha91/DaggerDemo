package com.sankha.daggerdemo2.di

import androidx.appcompat.app.AppCompatActivity
import com.sankha.daggerdemo2.login.view.LoginActivity
import com.sankha.daggerdemo2.splash.SplashScreenActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent (modules = arrayOf(PreLoginModule::class))
interface PreLoginComponent {

    fun injectSplashActivity(splashScreenActivity: SplashScreenActivity)
    fun injectLoginActivity(loginActivity: LoginActivity)

    @Subcomponent.Builder
    interface Builder{
        @BindsInstance
        fun activityContext(appCompatActivity: AppCompatActivity) : Builder

        @BindsInstance
        fun loginRequest(request : String) : Builder

        fun build() : PreLoginComponent
    }
}