package com.sankha.daggerdemo2.login.viewmodel

import com.sankha.daggerdemo2.login.model.UserResponseModel

interface LoginLocalDataSource {

    fun saveRequest(userResponseModel: UserResponseModel?)
}