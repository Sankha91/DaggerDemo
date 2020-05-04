package com.sankha.daggerdemo2.dashboard.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.sankha.daggerdemo2.MyApplication
import com.sankha.daggerdemo2.R
import com.sankha.daggerdemo2.dashboard.viewmodel.DashboardViewModel
import com.sankha.daggerdemo2.dashboard.viewmodel.DashboardViewModelFactory
import com.sankha.daggerdemo2.di.AppComponent
import com.sankha.daggerdemo2.di.PostLoginComponent
import com.sankha.daggerdemo2.login.model.UserResponseModel
import com.sankha.daggerdemo2.login.others.Constants
import com.sankha.daggerdemo2.login.others.Constants._KEY_USER_DETAILS
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject
import javax.inject.Named

class DashboardActivity : AppCompatActivity() {

    @Named("GotoLogin")
    @Inject
    lateinit var loginIntent: Intent
    @Named("GotoView")
    @Inject
    lateinit var viewIntent: Intent
    @Inject
    lateinit var editor: SharedPreferences.Editor
    @Inject
    lateinit var dashboardViewModelFactory: DashboardViewModelFactory

    lateinit var dashboardViewModel: DashboardViewModel
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

        dashboardViewModel = ViewModelProviders.of(this, dashboardViewModelFactory).get(DashboardViewModel::class.java)

        if (intent.hasExtra(_KEY_USER_DETAILS))
        userResponseModel = intent.getSerializableExtra(_KEY_USER_DETAILS) as UserResponseModel

        tvDescription.text = "Welcome "+userResponseModel.name +" !\n\nEmail : "+userResponseModel.email+"\nContact : "+userResponseModel.phone+"\n"+userResponseModel.address.toString()

     //   Log.e("MyApplicaton","editor...dashboard..."+editor.toString())
        tvLogout.setOnClickListener {
            editor.remove(_KEY_USER_DETAILS)
            editor.commit()
            Toast.makeText(this, "Logged Out Successfully !", Toast.LENGTH_LONG).show()
            startActivity(loginIntent)
            finish()
        }

        btSubmit.setOnClickListener {
            dashboardViewModel.insert(etNotes.text.toString())
        }

        dashboardViewModel.getInsertLiveData().observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Saved Successfully !", Toast.LENGTH_LONG).show()
                etNotes.text?.clear()
            }
            else Toast.makeText(this, "Oops! Something went wrong.", Toast.LENGTH_LONG).show()
        })

        tvView.setOnClickListener {
            startActivity(viewIntent)
        }
    }
}
