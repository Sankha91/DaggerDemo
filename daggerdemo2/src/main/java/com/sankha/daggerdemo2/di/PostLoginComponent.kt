package com.sankha.daggerdemo2.di

import androidx.appcompat.app.AppCompatActivity
import com.sankha.daggerdemo2.ViewActivity
import com.sankha.daggerdemo2.dashboard.view.DashboardActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent (modules = arrayOf(PostLoginModule::class))
interface PostLoginComponent {
    fun injectDashboardActivity(dashboardActivity: DashboardActivity)
    fun injectViewActivity(viewActivity: ViewActivity)

    @Subcomponent.Builder
    interface Builder{
        @BindsInstance
        fun activityContext(appCompatActivity: AppCompatActivity) : Builder

        fun build() : PostLoginComponent
    }
}