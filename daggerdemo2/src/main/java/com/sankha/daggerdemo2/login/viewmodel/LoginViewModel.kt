package com.sankha.daggerdemo2.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sankha.daggerdemo2.MyApplication.Companion.TAG
import com.sankha.daggerdemo2.login.model.UserResponseModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(var loginRepository: LoginRepository) : ViewModel() {

    fun getUser(id: String, rememberMe: Boolean){
        if (id.isNotEmpty()){
            Log.e(TAG, "inside LoginViewModel.."+id)
            loginRepository.getUser(id, rememberMe)
        }
    }

    fun getLoginLiveData():MutableLiveData<UserResponseModel?>{
        return loginRepository.loginLiveData
    }
}