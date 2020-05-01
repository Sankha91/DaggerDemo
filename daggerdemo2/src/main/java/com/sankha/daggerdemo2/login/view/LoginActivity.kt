package com.sankha.daggerdemo2.login.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sankha.daggerdemo2.MyApplication
import com.sankha.daggerdemo2.R
import com.sankha.daggerdemo2.di.AppComponent
import com.sankha.daggerdemo2.di.PreLoginComponent
import com.sankha.daggerdemo2.login.others.Constants
import com.sankha.daggerdemo2.login.viewmodel.LoginViewModel
import com.sankha.daggerdemo2.login.viewmodel.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject
import javax.inject.Named

class LoginActivity : AppCompatActivity() {

    @Named("GotoDashboard")
    @Inject
    lateinit var dashboardIntent: Intent
    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory

    lateinit var loginViewModel: LoginViewModel
    lateinit var appComponent: AppComponent
    lateinit var preLoginComponent: PreLoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        appComponent = (application as MyApplication).getAppComponent()
        preLoginComponent = appComponent.getPreLoginComponent()
            .loginRequest("")
            .activityContext(this)
            .build()
        preLoginComponent.injectLoginActivity(this)

        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        btSubmit.setOnClickListener {
          //  Log.e(MyApplication.TAG, "inside LoginActivity...")
            progressBar.visibility = View.VISIBLE
            tvError.visibility = View.GONE
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            loginViewModel.getUser(etId.text.toString(), checkbox.isChecked)
        }

        loginViewModel.getLoginLiveData().observe(this, Observer {
            progressBar.visibility = View.GONE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            if (it == null){
                tvError.visibility = View.VISIBLE
            }
            it?.let {
                Toast.makeText(this, "Successfully Get-In !",Toast.LENGTH_LONG).show()
                dashboardIntent.putExtra(Constants._KEY_USER_DETAILS, it)
                startActivity(dashboardIntent)
                finish()
            }
        })
    }
}


