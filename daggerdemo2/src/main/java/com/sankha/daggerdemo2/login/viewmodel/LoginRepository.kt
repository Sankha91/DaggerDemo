package com.sankha.daggerdemo2.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sankha.daggerdemo2.MyApplication
import com.sankha.daggerdemo2.login.model.UserResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginDataSource: LoginDataSource, private val loginLocalDataSource: LoginLocalDataSource) {

    @Inject
    lateinit var loginLiveData : MutableLiveData<UserResponseModel?>

    lateinit var userResponseModel: UserResponseModel

    fun getUser(id: String, rememberMe: Boolean) {
      //  Log.e(MyApplication.TAG, "inside LoginRepository_getUser."+id)
        loginDataSource.callLoginService(id).enqueue(object : Callback<UserResponseModel> {
            override fun onFailure(call: Call<UserResponseModel>?, t: Throwable?) {
                loginLiveData.postValue(null)
            }

            override fun onResponse(
                call: Call<UserResponseModel>?,
                response: Response<UserResponseModel>?
            ) {
                if (response?.isSuccessful ?: false){
                    response?.body()?.let {
                        userResponseModel = it
                        if (rememberMe) saveUserSession()
                        loginLiveData.postValue(it)
                    }
                }
                else loginLiveData.postValue(null)
            }

        })
    }

    fun saveUserSession(){
      //  Log.e(MyApplication.TAG, "inside LoginRepository_saveUserSession.")
        loginLocalDataSource.saveRequest(userResponseModel)
    }
}