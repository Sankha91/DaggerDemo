package com.sankha.daggerdemo2.login.viewmodel

import com.sankha.daggerdemo2.login.model.UserResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LoginService {

    @GET("users/{id}")
    fun callUserApi(@Path("id")id : String) : Call<UserResponseModel>
}