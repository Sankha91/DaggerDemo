package com.sankha.daggerdemo2.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

/* For Dagger2, we need this class to inject ViewModel classes
* For more info, refer : https://www.techyourchance.com/dependency-injection-viewmodel-with-dagger-2/ */
class LoginViewModelFactory @Inject constructor(private val loginViewModel: LoginViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(viewModelClass: Class<T>): T {
        val viewModel: ViewModel
        if (viewModelClass === LoginViewModel::class.java) {
            viewModel = loginViewModel
        } else {
            throw RuntimeException("unsupported view model class: $viewModelClass")
        }

        return viewModel as T
    }
}