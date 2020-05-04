package com.sankha.daggerdemo2

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sankha.daggerdemo2.dashboard.viewmodel.DashboardViewModel
import com.sankha.daggerdemo2.dashboard.viewmodel.DashboardViewModelFactory
import com.sankha.daggerdemo2.db.WordEntity
import com.sankha.daggerdemo2.di.AppComponent
import com.sankha.daggerdemo2.di.PostLoginComponent

import kotlinx.android.synthetic.main.activity_view.*
import javax.inject.Inject

class ViewActivity : AppCompatActivity() {

    @Inject
    lateinit var dashboardViewModelFactory: DashboardViewModelFactory

    lateinit var dashboardViewModel: DashboardViewModel
    lateinit var appComponent: AppComponent
    lateinit var postLoginComponent: PostLoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        appComponent = (application as MyApplication).getAppComponent()
        postLoginComponent = appComponent.getPostLoginComponent()
            .activityContext(this)
            .build()
        postLoginComponent.injectViewActivity(this)

        dashboardViewModel = ViewModelProviders.of(this, dashboardViewModelFactory).get(DashboardViewModel::class.java)

        dashboardViewModel.fetchAll()

        dashboardViewModel.fetchLiveData().observe(this, Observer {
            if (it.isNotEmpty()){
                tvNodata.visibility = View.GONE
                recyclerVw.visibility = View.VISIBLE
                with(recyclerVw){
                    adapter = ViewListAdapter(it as ArrayList<WordEntity>, this@ViewActivity)
                }
            }
            else{
                tvNodata.visibility = View.VISIBLE
                recyclerVw.visibility = View.GONE
            }
        })

    }

}
