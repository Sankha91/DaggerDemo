package com.sankha.daggerdemo2.login.viewmodel

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.sankha.daggerdemo2.MyApplication
import com.sankha.daggerdemo2.login.model.UserResponseModel
import com.sankha.daggerdemo2.login.others.Constants._KEY_USER_DETAILS
import retrofit2.Call
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(private val loginService: LoginService) : LoginDataSource,
    LoginLocalDataSource {

    @Inject
    lateinit var editor: SharedPreferences.Editor

    override fun saveRequest(userResponseModel: UserResponseModel?) {
     //   Log.e("MyApplicaton","editor..."+editor.toString())
        userResponseModel?.let {
            val jsonStr = Gson().toJson(userResponseModel)
            editor.putString(_KEY_USER_DETAILS, jsonStr)
            editor.apply()
        }
    }

    override fun callLoginService(request: String) : Call<UserResponseModel> {
     //   Log.e(MyApplication.TAG, "inside LoginDataSourceImpl...callLoginService"+request)
        return loginService.callUserApi(request)
    }
}