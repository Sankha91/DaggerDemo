package com.sankha.daggerdemo2.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun getPreLoginComponent() : PreLoginComponent.Builder
    fun getPostLoginComponent() : PostLoginComponent.Builder

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun appContext(application: Application) : Builder

        fun build() : AppComponent
    }
}