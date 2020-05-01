package com.sankha.daggerdemo2.dashboard

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.sankha.daggerdemo2.MyApplication
import com.sankha.daggerdemo2.R
import com.sankha.daggerdemo2.di.AppComponent
import com.sankha.daggerdemo2.di.PostLoginComponent
import com.sankha.daggerdemo2.login.model.UserResponseModel
import com.sankha.daggerdemo2.login.others.Constants
import com.sankha.daggerdemo2.login.others.Constants._KEY_USER_DETAILS
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject

class DashboardActivity : AppCompatActivity() {

    @Inject
    lateinit var loginIntent: Intent
    @Inject
    lateinit var editor: SharedPreferences.Editor

    lateinit var appComponent: AppComponent
    lateinit var postLoginComponent: PostLoginComponent
    lateinit var userResponseModel: UserResponseModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        appComponent = (application as MyApplication).getAppComponent()
        postLoginComponent = appComponent.getPostLoginComponent()
            .activityContext(this)
            .build()
        postLoginComponent.injectDashboardActivity(this)

        if (intent.hasExtra(_KEY_USER_DETAILS))
        userResponseModel = intent.getSerializableExtra(_KEY_USER_DETAILS) as UserResponseModel

        tvDescription.text = "Welcome "+userResponseModel.name +" !\n\nEmail : "+userResponseModel.email+"\nContact : "+userResponseModel.phone

     //   Log.e("MyApplicaton","editor...dashboard..."+editor.toString())
        tvLogout.setOnClickListener {
            editor.remove(_KEY_USER_DETAILS)
            editor.commit()
            Toast.makeText(this, "Logged Out Successfully !", Toast.LENGTH_LONG).show()
            startActivity(loginIntent)
            finish()
        }
    }
}
