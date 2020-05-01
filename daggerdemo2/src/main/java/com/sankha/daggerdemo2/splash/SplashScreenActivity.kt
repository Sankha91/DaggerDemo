package com.sankha.daggerdemo2.splash

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.RequestManager
import com.google.gson.Gson
import com.sankha.daggerdemo2.MyApplication
import com.sankha.daggerdemo2.R
import com.sankha.daggerdemo2.di.AppComponent
import com.sankha.daggerdemo2.di.PreLoginComponent
import com.sankha.daggerdemo2.login.model.UserResponseModel
import com.sankha.daggerdemo2.login.others.Constants
import com.sankha.daggerdemo2.login.others.Constants._KEY_USER_DETAILS
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class SplashScreenActivity : AppCompatActivity() {

    @Inject
    lateinit var requestManager : RequestManager
    @Inject
    lateinit var drawable : Drawable
    @Inject
    lateinit var timer: Timer

    @Named("GotoLogin")
    @Inject
    lateinit var loginIntent: Intent

    @Named("GotoDashboard")
    @Inject
    lateinit var dashboardIntent: Intent

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var appComponent: AppComponent
    lateinit var preLoginComponent: PreLoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        appComponent = (application as MyApplication).getAppComponent()
        preLoginComponent = appComponent.getPreLoginComponent()
            .loginRequest("")
            .activityContext(this)
            .build()
        preLoginComponent.injectSplashActivity(this)

        requestManager.load(drawable).into(findViewById(R.id.imageView))

        timer.schedule(object : TimerTask(){
            override fun run() {
             //   Log.e("MyApplicaton",sharedPreferences.toString())
                if (sharedPreferences.contains(_KEY_USER_DETAILS) &&
                    sharedPreferences.getString(_KEY_USER_DETAILS, "")?.isNotEmpty() ?: false){
                    val userResponseModel = Gson().fromJson(sharedPreferences.getString(_KEY_USER_DETAILS, ""), UserResponseModel::class.java)
                    dashboardIntent.putExtra(_KEY_USER_DETAILS, userResponseModel)
                    startActivity(dashboardIntent)
                }
                else startActivity(loginIntent)
                finish()
            }
        }, 3000)
    }
}
