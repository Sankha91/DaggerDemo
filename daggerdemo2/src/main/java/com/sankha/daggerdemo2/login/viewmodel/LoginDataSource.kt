package com.sankha.daggerdemo2.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sankha.daggerdemo2.login.model.UserResponseModel
import retrofit2.Call

interface LoginDataSource {

    fun callLoginService(request: String) : Call<UserResponseModel>
}