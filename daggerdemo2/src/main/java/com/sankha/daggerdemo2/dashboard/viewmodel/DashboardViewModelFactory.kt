package com.sankha.daggerdemo2.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


/* For Dagger2, we need this class to inject ViewModel classes
* For more info, refer : https://www.techyourchance.com/dependency-injection-viewmodel-with-dagger-2/ */
class DashboardViewModelFactory @Inject constructor(private val dashboardViewModel: DashboardViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(viewModelClass: Class<T>): T {
        val viewModel: ViewModel
        if (viewModelClass === DashboardViewModel::class.java) {
            viewModel = dashboardViewModel
        } else {
            throw RuntimeException("unsupported view model class: $viewModelClass")
        }

        return viewModel as T
    }
}